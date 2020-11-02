- 课后作业

> 1、按今天的课程要求，实现一个网关，基础代码可以fork:https://github.com/kimmking/JavaCourseCodes/tree/main/02nio/nio02 文件夹下，实现以后代码提交到github
>
> 1) 周四作业：整合你上次作业的 httpclient/okclient;
>
> 2) 周四作业(可选)：使用netty 实现后端 http访问（代替上一步）
>
> 3) 周六作业：实现过滤器 

```java
@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            //logger.info("channelRead流量接口请求开始，时间为{}", startTime);
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
//            String uri = fullRequest.uri();
//            //logger.info("接收到的请求url为{}", uri);
//            if (uri.contains("/test")) {
//                handlerTest(fullRequest, ctx);
//            }
            //课程中提到：在这里加filter
            //1、在这里将request的header中增加 nio:你的名字
            //	使用场景：在一定条件下可以控制request header中添加头的内容，在handler中实现路由，将请求			//发送给不同的服务
            //2、在hanhler中创建新的request将传入的request头都取出来放到新的request里
            
            handler.handle(fullRequest, ctx);
    
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
```
> 4) 周六作业(可选): 实现路由 