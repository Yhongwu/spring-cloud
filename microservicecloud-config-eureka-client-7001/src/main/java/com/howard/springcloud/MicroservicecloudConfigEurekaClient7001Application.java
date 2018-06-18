package com.howard.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * spring config 与eureka
 * config配置版的eureka
 */
@SpringBootApplication
@EnableEurekaServer
public class MicroservicecloudConfigEurekaClient7001Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicecloudConfigEurekaClient7001Application.class, args);
	}
}

// 测试21： 通过springconfig获取eureka配置
// github本地路径新建文件microservicecloud-config-eureka-client.yml和microservicecloud-config-dept-client.yml
// 内容分别对应tmp1.yml和tmp2.yml，分别对应eureka和dept的配置文件
// 新建config-eureka-7001模块，基本内容和7001差不多，pom新添加config的包
// 主启动类加上EnableEurekaServer注解
// 添加bootstrap.yml和application.yml配置文件，不再需要很详细的配置，而是通过config-3344提供的github地址去获取配置信息
// 启动3344 ，在启动config-eureka-7001 ，输入http://eureka7001.com:7001/测试eureka是否启动成功