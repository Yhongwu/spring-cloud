package com.howard.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard // hystrix监控
public class MicroservicecloudConsumerHystrixDashboard9001Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicecloudConsumerHystrixDashboard9001Application.class, args);
	}
}

// HystrixDashboard
// Hystrix提供了准实时的调用监控HystrixDashboard，hystrix会持续地记录所有通过hystrix发起的请求的执行信息，并以统计报表和图形的形式展示给用户
// 包括每秒执行多少请求多少成功多少失败

// 测试15： 服务监控（HystrixDashboard）
// 新建dashboard-9001，pom加入hystrix和hystrix-dashboard相关包
// 主启动类加上EnableHystrixDashboard注解
// 启动dashboard，输入http://localhost:9001/hystrix

// 测试16：监控hystrix-8001的信息
// 所有微服务提供类(这里测试指hystrix-8001) 都需要田间监控依赖配置 即pom添加actuator
// 启动7001、7002、7003 再启动hystrix-8001，再启动dashboard-9001
// 输入http://localhost:8001/dept/get/1多次刷新
// http://localhost:8001/hystrix.stream可以看到不断的监控信息
// 在http://localhost:9001/hystrix界面上的第一个输入框输入http://localhost:8001/hystrix.stream
// 下面分别输入2000和名称demo(随意) ，点击monitor stream可看到图形化的监控信息
// 输入http://localhost:8001/dept/get/1多次刷新 查看监控界面