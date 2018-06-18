package com.howard.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 部门微服务提供者
 * 8001 不对外真正调用(对外接口的端口为80)
 * 端口8001 提供服务端的接口
 */
@SpringBootApplication
@EnableEurekaClient //启动后此服务会自动注册到eureka
@EnableDiscoveryClient //服务发现
public class MicroservicecloudProviderDept8001Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicecloudProviderDept8001Application.class, args);
	}
}
// 测试1
// install microservicecloudapi，pom引入该公共包
// 单独启动 输入http://localhost:8001/dept/list测试

// 测试2：将dept-8001服务提供者注入eureka
// pom引入eureka的包
// yml配置
// 启动类增加@EnableEurekaClient
// 测试:先启动eureka-7001，再启动dept-8001让其注册进eureka
// 访问 http://localhost:7001/ 会发现本服务已经注册进eureka，同时服务名Application即yml中配置的spring.application.name: microservicecloud-dept
// 此时留意访问页status的链接名和浏览器左下角的细节

// 测试3：修改eureka的细节
// yml新增instance的配置
// 先启动eureka-7001，再启动dept-8001让其注册进eureka
// http://localhost:7001/测试 查看status访问路径 访问路径显示名和访问路径鼠标移上去浏览器左下角的显示

// 测试4： info细节
// 访问路径点击进去 默认为error或无任何信息。修改其显示细节
// pom添加actuator
// yml增加info配置

// 测试5：服务发现 即微服务暴露自身相关信息
// controller新增discovery方法暴露接口和信息
// 主启动类新增EnableDiscoveryClient允许服务发现
// 先后启动eureka-7001，dept-8001，输入http://localhost:8001/dept/discovery测试



