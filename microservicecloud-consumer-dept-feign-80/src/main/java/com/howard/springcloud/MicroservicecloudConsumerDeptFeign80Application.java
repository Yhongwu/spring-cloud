package com.howard.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan("com.howard.springcloud")
@EnableFeignClients(basePackages = {"com.howard.springcloud"})
public class MicroservicecloudConsumerDeptFeign80Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicecloudConsumerDeptFeign80Application.class, args);
	}
}

// 测试12 ： feign
// feign是声明式的webservice客户端，使用feign能让编写webservice客户端变得更加简单，它的使用方法是定义一个接口，然后再上面添加注解，feign可以
// 与eureke和ribbon组合以支持负载均衡
// 面向接口编程 如dao层
// 原先的80是通过调用dept-8001~8003的controller提供的接口
// 而这里将通过接口+注解的方式，获得服务，使得我们通过接口进行调用
// 由于接口可能为多个模块调用，所以写在api模块。在api模块中新建service层，添加接口DeptClientService，以rest的形式添加接口和注解，并加上fign的注解@FeignClient(value = "MICROSERVICECLOUD-DEPT")
// clean api模块，并install
// 新建feign-80模块，内容类似80模块，
// controller层的DeptConsumerController修改为注入DeptClientService接口的方式来调用服务，而不是80模块中的resttemple来直接调用controller的rest服务
// feign的主启动类添加EnableFeignClients注解
// 测试：启动eureke-7001、7002、7003，启动dept-8001、8002、8003
// 启动feign-80，输入http://localhost/consumer/dept/list 同样可以访问到数据