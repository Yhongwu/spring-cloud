package com.howard.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient //启动后此服务会自动注册到eureka
@EnableDiscoveryClient //服务发现
public class MicroservicecloudProviderDept8002Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicecloudProviderDept8002Application.class, args);
	}
}
