package io.github.kimmking.gateway.outbound.httpclient;

import io.github.kimmking.gateway.outbound.httpclient4.NamedThreadFactory;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import org.apache.http.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.*;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpOutboundHandlerMy {

    private CloseableHttpClient httpClient;
    private ExecutorService proxyService;
    private String backendUrl;

    public HttpOutboundHandlerMy(String backendUrl)
    {
        this.backendUrl = backendUrl.endsWith("/")?backendUrl.substring(0,backendUrl.length()-1):backendUrl;
        int cores = Runtime.getRuntime().availableProcessors() * 2;
        long keepAliveTime = 1000;
        int queueSize = 2048;
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();
        proxyService = new ThreadPoolExecutor(cores, cores,
                keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize),
                new NamedThreadFactory("proxyService"), handler);

        httpClient = HttpClientBuilder.create().build();
        System.out.println("HttpInboundHandler 初始化 HttpOutboundHandlerMy...");
    }

    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx) {
        final String url = this.backendUrl + fullRequest.uri();
        System.out.println("HttpInboundHandler 调用 HttpOutboundHandlerMy.handle...");
        proxyService.submit(()->fetchGet(fullRequest, ctx, url));
    }

    private void fetchGet(final FullHttpRequest inbound, final ChannelHandlerContext ctx, final String url) {
        final HttpGet httpGet = new HttpGet(url);
        for (Map.Entry e : inbound.headers()) {
            httpGet.addHeader(e.getKey().toString(),e.getValue().toString());
            System.out.println("inbound     "+e.getKey() + " => " + e.getValue());
        }
        for (Header e : httpGet.getAllHeaders()) {
            System.out.println("new httpGet    "+e.getName() + " => " + e.getValue());
        }
        CloseableHttpResponse callResponse;
        FullHttpResponse response = null;
        try {
            System.out.println("httpClient 请求url="+url);
            callResponse = httpClient.execute(httpGet);
            byte[] body = EntityUtils.toByteArray(callResponse.getEntity());;
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", body.length);
            ctx.write(response);
/*************我创建的BasicHttpResponse不能输出到浏览器*****************************************************
//            HttpResponse rtnResponse = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK,"OK");
//            rtnResponse.addHeader("Content-Type", "application/json");
//            rtnResponse.addHeader("Content-Length",String.valueOf(body.length));
//            ByteArrayEntity entity = new ByteArrayEntity(body);
//            rtnResponse.setEntity(entity);
 *********************************************************************8/
/*************老师Demo的可以write到浏览器*********************************************
//            String value = "hello,kimmking";
//            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
//            response.headers().set("Content-Type", "application/json");
//            response.headers().setInt("Content-Length", response.content().readableBytes());
//            System.out.println("ctx.write rtnResponse");
********************************************************/
        } catch (Exception e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
            exceptionCaught(ctx, e);
        } finally {
            if (inbound != null) {
                if (!HttpUtil.isKeepAlive(inbound)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    //response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
            }
            ctx.flush();
            //ctx.close();
        }
    }
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
