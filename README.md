# CRS3

汽车租赁系统系列课程设计——CRS3

## 简介

​		汽车租赁系统3.0的开发旨在引入现代化的技术和方法，以满足不断增长的用户需求。通过引入Spring Boot框架、MongoDB数据库、Redis缓存、多线程技术和Hadoop MapReduce。这个系统不仅为用户提供了更多的功能，还可以实现各种日志记录、热度排行榜和多线程缓存，从而进一步提高了系统的稳定性和性能。

【关键字】汽车租赁、MongoDB、Redis、Hadoop MapReduce

​		The development of Car Rental System 3.0 aims to introduce modern technologies and methods to meet the growing user needs. By introducing the Spring Boot framework, MongoDB database, Redis cache, multi-threading technology and Hadoop MapReduce. This system not only provides users with more functions, but can also implement various logging, popularity rankings, and multi-thread caching, thereby further improving the stability and performance of the system.

[Keywords] Car rental, MongoDB, Redis, Hadoop MapReduce

## 更新

### MongoDB日志记录

启动日志： 记录系统的启动时间、启动主机名和IP地址。

用户登录日志： 记录登录的用户名、登录时间、登录状态、日志可见性和权限级别（Customer/Admin/Visitor/Null）。

留言动态日志： 记录操作时间、操作是否成功、操作权限、操作人以及操作内容，包括留言发表、查看、删除、隐藏和展示等操作。

### Redis热度排行榜

用户浏览车辆会为指定车辆增加5点热度值。

用户完成车辆租赁会为指定车辆增加10点热度值。

### Redis + MongoDB多线程缓存机制

利用多线程技术，实现Redis和MongoDB的同步机制，每10秒同步一次数据。

通过缓存机制，面向热度排行榜，提高系统性能。

### 多线程访问网络实现日志记录

使用多线程技术，后台获取和记录IP地址的启动日志，以提高运行效率。

### Hadoop本地MapReduce实现爱好联想

通过MapReduce技术，联想两两用户共同的喜好。数据准备阶段包括从MySQL抽取订单数据，拼接键值，同键合并，键值反转，单键剥离，多键两两拆分等操作。

## 系统架构

汽车租赁系统3.0采用分层设计，包括以下组件：

**Service层：** 实现业务逻辑，包括用户登录、租车还车、留言管理、日志记录等功能。

**Entity层：** 定义系统的数据实体。

**Mapper层：** 提供数据持久化操作接口，与MySQL数据库交互。

**DB Work Space层：** 数据库连接池技术，管理数据库连接。

**Command Control层：** 用于实现指令控制，系统软中断。

**Configs层：** 配置管理层

**Map Reduce For CV Like层：** Map Reduce操作集合，实现两两用户的喜好联想。

**Other Thread层：** 下设两个线程，一是用于缓存同步，二是用于启动日志记录。

**Repository Mongo层：** 用于与MongoDB交互的接口创建。

**Repository Redis层：** 用于与Redis交互的接口创建。

**Tools层：** 工具层

**View层：** 视图层

## 技术栈

**Spring Boot：** 用于构建应用。

**MyBatis：** 数据库持久层框架。

**Maven：** 构建管理工具。

**MongoDB：**  用于存储各种日志数据，包括启动日志、登录日志、留言动态日志。

**Redis：** 用于实现热度排行榜和多线程缓存机制。

**Hadoop：** 用于本地MapReduce任务，实现用户喜好联想。

**Spring Boot Data系列；** 用于快捷操作NoSQL数据库。


## Hadoop MapReduce

通过MapReduce实现爱好联想功能。

  通过分析，我将这个功能模块分为三个部分，分别是：预处理部分、一轮Map Reduce部分、二轮MapReduce部分。

  首先，预处理阶段先从MySQL中拿到订单记录，用户租了哪辆车就代表他相对比较喜欢这两，因此得到如下数据：
  
![image](https://github.com/735690757/CRS3/assets/51825900/134b8d8e-5120-42fa-bb0c-1ca4a896d4c5)

  （车）=（人），这个表达式代表着这个车被这个用户所喜欢。

  接下来进入第一轮MapReduce，其实这个预处理阶段就在逻辑上包含了一轮Map的一部分所以一轮Map1还是上面的数据。Shuffle1阶段是将左侧的键值合并，这样就得到了：（车）=（人1，人2，人3），也就是说这个车同时被人1，人2，人3所喜欢。Reduce1阶段将键值反转，因此得到了：（人1，人2，人3）=（车），其实意思是一样的。经过一轮MapReduce之后，可以看到左侧人有点多，我们的要求是两两之间的共同喜好，所以需要进行二轮MapReduce。下图展示了一轮MR的结果。

![image](https://github.com/735690757/CRS3/assets/51825900/3a57d815-83f7-4642-9a8b-b0c735cc79ae)


  接下来进行二轮MapReduce，首先将一轮MapReduce的结果进行二轮Map2。Shuffle2将左侧三个及三个以上的键拆分（要先排序，如果不排序可能就会出现：A-B，B-A的情况）。Reduce2阶段将单一键取代，保留对键。下面是MR的结果。

![image](https://github.com/735690757/CRS3/assets/51825900/46ecd2fb-a4a4-4395-9058-896d3d2799fe)


  显然已经得到了结果。
