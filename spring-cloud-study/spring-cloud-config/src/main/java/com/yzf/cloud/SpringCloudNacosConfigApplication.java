package com.yzf.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;

@EnableDiscoveryClient
@SpringBootApplication
@RefreshScope
public class SpringCloudNacosConfigApplication {

	public static void main(String[] args) {

//		https://www.cnblogs.com/larscheng/p/11416392.html
//		https://www.cnblogs.com/l-y-h/p/14604209.html
		ConfigurableApplicationContext context=
				SpringApplication.run(SpringCloudNacosConfigApplication.class, args);
//		String info=context.getEnvironment().getProperty("info");
//		System.out.println(info);
	}
}
