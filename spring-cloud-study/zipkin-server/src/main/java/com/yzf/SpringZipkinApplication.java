/*
 * author: wangjian (jonath@163.com)
 * Copyright 2019
 */
package com.yzf;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin2.server.internal.EnableZipkinServer;

/**
 * TODO
 * author: wangjian (jonath@163.com)
 * date: 2021/7/22 11:24
 */
@EnableZipkinServer
@EnableDiscoveryClient
@SpringBootApplication
public class SpringZipkinApplication {
    // https://zipkin.io/pages/architecture.html
    // https://www.jianshu.com/p/2fcbc8bba1c1
    // https://github.com/cicadasmile/spring-cloud-base
    // java -jar D:\jar\zipkin-server-2.12.9-exec.jar --STORAGE_TYPE=mysql --MYSQL_HOST=localhost --MYSQL_TCP_PORT=3306 --MYSQL_DB=zipkin --MYSQL_USER=root --MYSQL_PASS=root
//    https://github.com/openzipkin/zipkin/blob/master/zipkin-storage/mysql/src/main/resources/mysql.sql
    // wget -O zipkin.jar 'https://search.maven.org/remote_content?g=io.zipkin.java&a=zipkin-server&v=LATEST&c=exec'
    public static void main(String[] args) {

//        $ curl -sSL https://zipkin.io/quickstart.sh | bash -s
//         或直下载：https://search.maven.org/remote_content?g=io.zipkin.java&a=zipkin-server&v=LATEST&c=exec
//        $ java -jar zipkin.jar
//        默认情况下，Zipkin服务器将在port上运行9411。将浏览器导航到http://localhost:9411/zipkin/
// docker 启动：https://blog.csdn.net/qq_30346413/article/details/103920568
        org.springframework.boot.SpringApplication.run(SpringZipkinApplication.class, args);

        /**
         * 参考启动采坑：https://blog.csdn.net/LO_YUN/article/details/102684482
         * 启动后无法访问采坑 https://www.cnblogs.com/sxdcgaq8080/p/10007735.html
         */
        /**
         * 其实，我们仔细想想也可以总结出这种方式的几种缺陷
         * 缺陷1：zipkin客户端向zipkin-server程序发送数据使用的是http的方式通信，每次发送的时候涉及到连接和发送过程。
         * 缺陷2：当我们的zipkin-server程序关闭或者重启过程中，因为客户端收集信息的发送采用http的方式会被丢失。
         *
         * 针对以上两个明显的缺陷，改进的办法是
         * 1、通信采用socket或者其他效率更高的通信方式。
         * 2、客户端数据的发送尽量减少业务线程的时间消耗，采用异步等方式发送收集信息。
         * 3、客户端与zipkin-server之间增加缓存类的中间件，例如redis、MQ等，在zipkin-server程序挂掉或重启过程中，客户端依旧可以正常的发送自己收集的信息。
         * ————————————————
         * 原文链接：https://blog.csdn.net/wudiyong22/article/details/78930250
         */
    }
}
