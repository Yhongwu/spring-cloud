package com.howard.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class MicroservicecloudZuulGateway9527Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicecloudZuulGateway9527Application.class, args);
	}
}

// 测试17： zuul
// zuul包含了对请求的路由和过滤两个主要的功能：
// 其中路由功能负责将外部请求转发到具体的微服务实例上，是实现外部访问统一入口的基础，而过滤功能负责对请求的处理过程进行干预，是
// 实现请求校验、服务聚合等功能的基础
// zuul和eureka进行整合，zuul自身注册为eureka服务治理下的应用，同时从eureka获取其他微服务的消息。即以后的访问微服务都是通过zuul跳转获得
// 新建zuul-9527模块，pom引入zuul
// yml配置端口和注册eureka的信息
// host注册myzuul.9527.com对应127.0.0.1 模拟不同机器
// 主启动类添加@EnableZuulProxy
// 启动7001、7002、7003、启动dept-8001，启动zuul-9527
// 访问http://myzuul9527.com:9527/microservicecloud-dept/dept/get/2可以获取数据

// 测试18 ：
// 测试17中访问链接中直接通过微服务名microservicecloud-dept访问
// 可以在配置中代理该名字 隐藏服务名
// http://myzuul9527.com:9527/howard/mydept/dept/get/2