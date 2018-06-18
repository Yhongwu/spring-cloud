package com.howard.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient //启动后此服务会自动注册到eureka
@EnableDiscoveryClient //服务发现
@EnableCircuitBreaker //对hystrix熔断机制的支持
public class MicroservicecloudProviderDeptHystrix8001Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicecloudProviderDeptHystrix8001Application.class, args);
	}
}

// 测试13： hystrix熔断机制
// 多个微服务调用时，假设微服务a调用b和c，b和c又调用其他微服务，此为“扇出”
// 如果扇出链路上面某个微服务的调用响应时间长或者不可用，对a的调用就会占用越来越多的资源，进而引起系统奔溃，即“雪崩效应”
// hystrix用于处理分布式系统的延迟和容错的开源库。
// 断路器本身是一个开关装置，当某个服务单元发生故障后，通过断路器的监控，向调用方返回一个符合预期的可处理的响应fallback，
// 而不是长期等待或者抛出无法处理的异常。(个人理解，类似异常处理)
// 熔断
// 新建hystrix-8001 类似dept-8001，修改instance-id以区别dept-8001
// pom引入hystrix的jar
// 修改DeptController中的get方法，使其在获取到的dept为null抛出异常，并添加HystrixCommand指定方法去处理它，即返回可处理的响应
// 主启动类添加@EnableCircuitBreaker对熔断机制的支持
// 测试：启动eureka-7001、7002、7003，启动hystrix-8001.启动feign-80
// 访问http://localhost/consumer/dept/get/1 和http://localhost/consumer/dept/get/1000 查看是否都有返回值

// 测试14： 服务降级
// 如上面的测试13 每次都要对熔断的处理加到controller中，与业务逻辑耦合。所以可以考虑将hystrix的处理加到接口中，即类似aop切面并进行处理的方式
// 修改api模块，新建DeptClientServiceFallbackFactory，继承FallbackFactory<DeptClientService>，并注意添加Component注解
// 并自定义异常的处理返回信息
// 这里主要演示服务降级，即当某个服务不可用时，可以立即返回可处理信息，而不是一味等待响应。
// 以get为例，在DeptClientServiceFallbackFactory中对get进行处理，同时DeptClientService修改FeignClient注解，增加fallbackFactory属性
// clean install api模块
// feign的yml新增对服务降级的支持：feign.hystrix.enabled: true
// 启动eureka-7001、7002、7003，dept-8001、feign-80
// 输入http://localhost/consumer/dept/get/1测试 可返回正常信息
// 此时故意关闭dept-8001服务，再次访问http://localhost/consumer/dept/get/1 可以看到立即出现服务不可用的提示

// hystrix是客户端的一种技术
// 服务熔断：一般是某个服务故障或者异常引起，类似现实世界中的保险丝，当某个异常条件被触发，直接熔断整个服务，而不是移植等到此服务超时
// 服务降级：所谓降级，一般是从整体负荷考虑。就是当某个服务熔断之后，服务器将不再被调用。
// 此时客户端可以自己准备一个本地的fallback回调，返回缺省值
// 这样做，虽然服务水平下降，但还可用，总比直接挂掉好。