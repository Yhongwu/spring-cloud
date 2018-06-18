package com.howard.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient //启动后此服务会自动注册到eureka
@EnableDiscoveryClient //服务发现
public class MicroservicecloudConfigDeptClient8001Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicecloudConfigDeptClient8001Application.class, args);
	}
}

// 测试22 ： dept通过config3344获取配置
// github上提交microservicecloud-config-dept-client.yml，内容如tmp2.yml
// microservicecloud-config-dept-client.yml上，dev环境连接的是cloudb01数据库，test连接的是cloudb02数据库
// 新建config-dept-8001模块
// 内容与dept-8001类似，pom加入config的包，配置文件添加如application.yml和bootstrap.yml，通过3344获取github的统一配置
// 启动3344 启动config-eureka-7001，启动config-dept-8001.访问http://localhost:8001/dept/list注意获取的数据哪个数据库
// 切换环境，重启config-dept-8001测试
// 另一种测试：
// 修改github路径下的microservicecloud-config-dept-client.yml文件，将test环境由访问cloudb02换为cloudb03
// 提交数据到github
// 此时不需要修改微服务配置，服务自动切换为cloudb03数据库