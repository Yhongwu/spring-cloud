package com.howard.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * spring config 服务端
 */
@SpringBootApplication
@EnableConfigServer
public class MicroservicecloudConfig3344Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicecloudConfig3344Application.class, args);
	}
}

// 微服务意味着业务拆分成很多子服务，每个子服务都有自己的配置，管理困难
// springcloud config为微服务架构中的微服务提供集中化的外部配置支持，配置服务器为各个不同微服务应用提供了一个中心化的外部配置
// springcloud config分为服务端和客户端
// 服务端也称分布式配置中心 是一个独立的微服务应用。用来连接配置服务器并为客户端提供获取配置信息，加解密信息等访问接口
// 客户端则是通过指定的配置中心来管理应用资源，以及与业务相关的配置内容，并在启动的时候从配置中心获取和加载配置信息，
// 配置服务器默认用git来存储配置信息，有助于进行版本管理

// 测试19
// github新建microservicecloud-config仓库
// 本地路径新建目录并clone该仓库
// 在本地目录下新建配置文件,内容如application_git.yml并提交到github
// 新建项目config-3344 并pom导入config包
// yml配置git的访问url
// 主启动类添加注解@EnableConfigServer
// 启动config-3344 浏览器访问http://127.0.0.1:3344/application-dev.yml http://127.0.0.1:3344/application-test.yml
// 为模拟不同机器，可在host配置域名映射如127.0.0.1 config-3344.com
// 继续测试：访问 http://config-3344.com:3344/application/dev/master
// 访问路径有一定的规则

