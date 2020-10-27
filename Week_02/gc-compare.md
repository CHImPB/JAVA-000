# Week02 第一课

## 1 GC日志解读和分析

```java

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
/*
演示GC日志生成与解读
*/
public class GCLogAnalysis {
    private static Random random = new Random();
    public static void main(String[] args) {
        // 当前毫秒时间戳
        long startMillis = System.currentTimeMillis();
        // 持续运行毫秒数; 可根据需要进行修改
        long timeoutMillis = TimeUnit.SECONDS.toMillis(1);
        // 结束时间戳
        long endMillis = startMillis + timeoutMillis;
        LongAdder counter = new LongAdder();
        System.out.println("正在执行...");
        // 缓存一部分对象; 进入老年代
        int cacheSize = 2000;
        Object[] cachedGarbage = new Object[cacheSize];
        // 在此时间范围内,持续循环
        while (System.currentTimeMillis() < endMillis) {
            // 生成垃圾对象
            Object garbage = generateGarbage(100*1024);
            counter.increment();
            int randomIndex = random.nextInt(2 * cacheSize);
            if (randomIndex < cacheSize) {
                cachedGarbage[randomIndex] = garbage;
            }
        }
        System.out.println("执行结束!共生成对象次数:" + counter.longValue());
    }

    // 生成对象
    private static Object generateGarbage(int max) {
        int randomSize = random.nextInt(max);
        int type = randomSize % 4;
        Object result = null;
        switch (type) {
            case 0:
                result = new int[randomSize];
                break;
            case 1:
                result = new byte[randomSize];
                break;
            case 2:
                result = new double[randomSize];
                break;
            default:
                StringBuilder builder = new StringBuilder();
                String randomString = "randomString-Anything";
                while (builder.length() < randomSize) {
                    builder.append(randomString);
                    builder.append(max);
                    builder.append(randomSize);
                }
                result = builder.toString();
                break;
        }
        return result;
    }
}
```

# 作业

## week02_01

### 1、使用GCLogAnalysis.java自己演练一遍串行/并行/CMS/G1的案例

环境：WIN10、JDK8 、2核cpu、4G可用内存

```
-XX:+UseSerialGC 使用串行GC

-Xms512m 初始堆大小
-Xmx512m 最大堆大小
-Xloggc:serialGC.demo.log 将GC日报保存名为serialGC.demo.log的文件
-XX:+PrintGCDetails 打印GC明细
-XX:+PrintGCDateStamps 打印GC时间戳

```

```
def new generation 年轻代
	eden space 新生代
	from space 存货区0
	to   space 存货区1
tenured generation 老年代
Metaspace 元数据
	class space
```

分别在128m、512m、1024m、2048m、3072m、4096m 内存下测试 串行/并行/CMS/G1

```
java -XX:+UseSerialGC -Xms512m -Xmx512m  -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
java -XX:+UseParallelGC -Xms512m -Xmx512m  -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
java -XX:+UseConcMarkSweepGC -Xms512m -Xmx512m  -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
java -XX:+UseG1GC -Xms512m -Xmx512m  -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
```

产生对象数量/YoungGC次数/OldGC次数

SerialGC 5次

2048m 每次YoungGC耗时:83ms~109ms

| Memory | 1        | 2         | 3        | 4         | 5         |
| ------ | -------- | --------- | -------- | --------- | --------- |
| 128m   | OOM      | OOM       | OOM      | OOM       | OOM       |
| 512m   | 6882/9/4 | 6925/10/3 | 6729/7/4 | 7318/10/3 | 7469/10/4 |
| 1024m  | 8384/7/0 | 8268/7/0  | 8450/8/0 | 8359/8/0  | 8827/8/0  |
| 2048m  | 8434/4/0 | 8569/4/0  | 7268/3/0 | 9456/4/0  | 7862/4/0  |
| 3072m  | 9214/2/0 | 7425/2/0  | 9308/2/0 | 9320/2/0  | 6266/2/0  |
| 4048m  | 6897/1/0 | 5103/1/0  | 8252/2/0 | 7424/1/0  | 7771/1/0  |

ParallelGC 5次，

512m   每次YoungGC耗时:11ms~23ms,每次ParallelGC 耗时：63ms~73ms

1024m 每次YoungGC耗时:22ms~43ms,每次ParallelGC 耗时：91ms~96ms

2048m 每次YoungGC耗时:47ms~62ms

3072m 每次YoungGC耗时:56ms~103ms

4048m 每次YoungGC耗时:68ms~117ms

| Memory | 1         | 2         | 3         | 4         | 5         |
| ------ | --------- | --------- | --------- | --------- | --------- |
| 128m   | OOM       | OOM       | OOM       | OOM       | OOM       |
| 512m   | 5664/17/3 | 5834/19/3 | 6313/21/4 | 6439/21/3 | 6287/20/3 |
| 1024m  | 8602/12/1 | 8571/12/1 | 8487/12/1 | 8610/12/1 | 8535/12/1 |
| 2048m  | 9429/4/0  | 10135/5/0 | 8485/4/0  | 10466/4/0 | 9869/4/1  |
| 3072m  | 8883/3/0  | 8874/3/0  | 9876/3/0  | 10181/3/0 | 8877/3/0  |
| 4048m  | 7714/1/0  | 8501/2/0  | 7831/2/0  | 9358/2/0  | 6082/1/0  |

ConcMarkSweepGC 5次，

512m   每次YoungGC耗时:14ms~31ms,并发CMS STW耗时：0.1ms~0.4ms

1024m 每次YoungGC耗时:24ms~58ms,每次CMS  STW耗时：3ms

2048m 每次YoungGC耗时:29ms~62ms

3072m 每次YoungGC耗时:27ms~56ms

4048m 每次YoungGC耗时:68ms~117ms

| Memory | 1         | 2         | 3         | 4         | 5         |
| ------ | --------- | --------- | --------- | --------- | --------- |
| 128m   | OOM       | OOM       | OOM       | OOM       | OOM       |
| 512m   | 7351/11/4 | 7354/10/4 | 7003/10/3 | 7398/10/4 | 7287/12/4 |
| 1024m  | 8518/8/2  | 9274/9/2  | 9022/8/2  | 7888/7/1  | 8286/8/2  |
| 2048m  | 8641/8/0  | 9834/9/0  | 8217/8/0  | 9192/9/0  | 7546/7/0  |
| 3072m  | 9148/9/0  | 9242/9/0  | 7997/7/0  | 9153/9/0  | 8253/8/0  |
| 4048m  | 8798/8/0  | 8424/8/0  | 8186/8/0  | 8543/8/0  | 9515/9/0  |

G1GC 5次

512m   每次YoungGC耗时:14ms~31ms,并发CMS STW耗时：0.1ms~0.4ms

1024m 每次YoungGC耗时:24ms~58ms,每次CMS  STW耗时：3ms

2048m 每次YoungGC耗时:29ms~62ms

3072m 每次YoungGC耗时:27ms~56ms

4048m 每次YoungGC耗时:68ms~117ms

| Memory | 1         | 2         | 3         | 4         | 5         |
| ------ | --------- | --------- | --------- | --------- | --------- |
| 128m   | OOM       | OOM       | OOM       | OOM       | OOM       |
| 512m   | 6543      | 6351      | 5913      | 5816      | 5623      |
| 1024m  | 8357      | 8723      | 7683      | 8117      | 7665      |
| 2048m  | 7316/12/0 | 9289/13/0 | 8960/13/0 | 7169/12/0 | 9590/13/0 |
| 3072m  | 8900/10/0 | 7630/10/0 | 9311/11/0 | 9292/12/0 | 7491/9/0  |
| 4048m  | 8452/10/0 | 8545/10/0 | 8508/10/0 | 9528/10/0 | 9694/11/0 |



平均统计

| GC/Memory       | 128m | 512m   | 1024m  | 2048m  | 3072m  | 4048m  |
| --------------- | ---- | ------ | ------ | ------ | ------ | ------ |
| SerialGC        | OOM  | 7064.6 | 8457.3 | 8317.8 | 8306   | 7089   |
| ParallelGC      | OOM  | 6107.4 | 8561   | 9676.8 | 9339   | 8041   |
| ConcMarkSweepGC | OOM  | 7278.6 | 8597.6 | 8668   | 8758.6 | 8693.2 |
| G1GC            | OOM  | 6049   | 8109   | 8208   | 8524.8 | 8945.4 |

通过上面数据可以看出：

​	1、SerialGC/ParallelGC/ConcMarkSweepGC 的性能是曲线分布的，随着内存增大性能增大然后减小，G1GC是随着内存增大性能增加的。

​	2、在512m时SerialGC和ConcMarkSweepGC性能相对比ParallelGC和G1GC好

​	3、在1024m时erialGC/ParallelGC/ConcMarkSweepGC性能相差不大

​	4、在2g~3g时ParallelGC性能最好

​    5、gc回收耗时是随着内存增大，回收时间是增大的	



### 2、使用压测工具(wrk或sb)，演练gateway-server-0.0.1-SNAPSHOT.jar示例

Requests/RPS

| GC/Memory       | 256m       | 512m        | 1024m       | 2048m | 3072m |
| --------------- | ---------- | ----------- | ----------- | ----- | ----- |
| SerialGC        | 3583/168.8 | 11470/542.2 | 14027/667.1 |       |       |
| ParallelGC      |            |             |             |       |       |
| ConcMarkSweepGC |            |             |             |       |       |
| G1GC            |            |             |             |       |       |



### 3、(选座)如果自己本地有可以运行的项目，可以按照2的方式进行演练。

### 根据上述自己对于1和2的演示，写一段对于不同GC的总结，提交到github

暂时没有

## week02-02

### 1、（可选）运行课上的例子，以及Netty的例子，分析相关现象。



### 2、写一段代码，使用HttpClient或OkHttp访问 http://localhost:8801,代码提交到github

```java
package com.demo;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class RequestNIO {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("http://localhost:8801");

        CloseableHttpResponse response;
        try {
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            System.out.println("响应状态码：" + response.getStatusLine());
            String result = entity.getContent().toString();
            System.out.println("返回内容：" + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

