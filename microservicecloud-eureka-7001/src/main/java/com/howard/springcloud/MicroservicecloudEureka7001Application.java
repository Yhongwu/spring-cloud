package com.howard.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * eureka服务端的启动类
 * 接受其它服务注册进来
 */
@SpringBootApplication
@EnableEurekaServer
public class MicroservicecloudEureka7001Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicecloudEureka7001Application.class, args);
	}
}

// eureka服务注册与发现
// 类似dubbo的服务注册中心zookeeper
//Netflix在设计eureka时遵循ap原则
// 采用c/s设计架构 server为服务注册中心，其他注册进来的为client，client注册到eureka并维持心跳
// eureka通过心跳检测和客户端缓存来实现其灵活与高可用
// 客户端缓存：客户端会缓存eureka的注册表，使得万一eureka宕机，服务调用不会马上出现问题

//测试1
//单独启动microservicecloud-eureka-7001 ，访问localhost:7001 ，出现eureka的界面

// eureka的自我保护机制
// 某一时刻某个微服务不可用了，eureka不会立即清理，而是会依旧对该微服务的信息进行保存
// 设计哲学：宁可保留错误的注册服务信息，也不盲目注销任何可能健康的服务实例
// 可通过enable-self-preservation配置

// 测试7：eureka的集群配置
// 为了区分，模拟服务在不同机器上，使用SwitchHosts工具配置：
//    127.0.0.1 eureka7001.com
//	  127.0.0.1 eureka7002.com
//    127.0.0.1 eureka7003.com
// 新建eureka-7002 eureka-7003，基本配置与eureka一致
// 分别修改eureka-7001、7002、7003的defaultZone和hostname使其形成集群
// 修改dept-8001的yml的defaultZone，使其启动后注册进三个eureka中
// 启动eureka-7001、7002、7003，启动dept-8001
// 分别输入http://eureka7001.com:7001/ http://eureka7002.com:7002/ http://eureka7003.com:7003/
// 可以看到DS Replicas显示另外两个eureka的地址 并且每个eureka都有dept-8001的实例

