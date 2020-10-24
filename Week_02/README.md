学习笔记

[TOC]

# 环境准备

## Windows

1.管理员身份打开powershell

2.运行

Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1'))

3.执行`choco install superbenchmarker`

4.输入 `sb`

执行 `sb -u http://localhost:8088/api/hello -c 20 -N 60`

**实际操作**

- 1 以管理员身份运行powershell

- 2 安装choco后运行`choco install superbenchmarker`出现下面错误

  ![image-20201024120052243](https://github.com/CHImPB/JAVA-000/blob/main/Week_02/doc_img/image-20201024120052243.png)

- 3 报读后执行`iwr https://chocolatey.org/install.ps1 -UseBasicParsing | iex`，出现下面错误

  ![image-20201024120737674](https://github.com/CHImPB/JAVA-000/blob/main/Week_02/doc_img/image-20201024120737674.png)

- 4 设置系统环境变量chocolateyUseWindowsCompression = 'true'，

  ![image-20201024120952221](https://github.com/CHImPB/JAVA-000/blob/main/Week_02/doc_img/image-20201024120952221.png)

  

- 5 再次执行`iwr https://chocolatey.org/install.ps1 -UseBasicParsing | iex` 安装成功

  ![image-20201024122325187](https://github.com/CHImPB/JAVA-000/blob/main/Week_02/doc_img/image-20201024122325187.png)

- 执行`choco install superbenchmarker`出现下面的错误

  ![image-20201024122431532](https://github.com/CHImPB/JAVA-000/blob/main/Week_02/doc_img/image-20201024122431532.png)

- 最后翻墙解决安装问题

  ![image-20201024124852044](https://github.com/CHImPB/JAVA-000/blob/main/Week_02/doc_img/image-20201024124852044.png)

## Mac

1.执行brew install wrk
如果显式brew update很慢，可以ctrl+C打断更新

2.输入 wrk

执行 wrk -t8 -c40 -d60s http://localhost:8088/api/hello

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

- 运行编译命令 `javac -encoding utf-8 GCLogAnalysis.java`

  出现下面的错误，用npp确认文件编码格式是否是utf-8,如果不是转换编码格式后继续编译：

   ![image-20201024121656847](https://github.com/CHImPB/JAVA-000/blob/main/Week_02/doc_img/image-20201024121656847.png)

  编码格式

 
