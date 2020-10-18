学习笔记

- 测试 git push -u origin master


#Week01 共两次直播课程，每次直播后，秦老师都会布置作业题目。
目前第一次直播（10月15日周四）作业题目如下：
#Week01 作业题目（周四）：
（选做）、自己写一个简单的 Hello.java，里面需要涉及基本类型，四则运行，if 和 for，然后自己分析一下对应的字节码，有问题群里讨论。
（必做）、自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。文件群里提供。
（必做）、画一张图，展示 Xmx、Xms、Xmn、Meta、DirectMemory、Xss 这些内存参数的关系。
Xms 堆初始化最小内存
Xmx 堆可使用最大内容
Xmn 设置年轻代大小
Xss 每个单线程堆栈大小
Meta 元空间大小
DirectMemory 
（选做）、检查一下自己维护的业务系统的 JVM 参数配置，用 jstat 和 jstack、jmap 查看一下详情，并且自己独立分析一下大概情况，思考有没有不合理的地方，如何改进。