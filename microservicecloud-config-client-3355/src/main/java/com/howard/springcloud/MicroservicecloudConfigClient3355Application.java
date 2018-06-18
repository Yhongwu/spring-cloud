package com.howard.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * spring config 客户端
 */
@SpringBootApplication
public class MicroservicecloudConfigClient3355Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicecloudConfigClient3355Application.class, args);
	}
}

// 测试20 ：springconfig 客户端
// 在github的microservicecloud-config本地路径上新建文件microservicecloud-config-client.yml，内容如tmp.yml，提交到github
// 新建模块config-client-3355，并导入config相关包
// 新建文件bootstrap.yml，bootstrap具有最高优先级，不会被其他文件覆盖
// 新建ConfigClientRest类，设置变量用于获取从3344提供的github地址获取的配置信息
// 启动3344 后启动3355 ，注意bootstrap.yml中当前配置的是dev还是test环境，不同环境访问的端口不一样。
// 如果为dev：http://client-config.com:8201/config，切换另一个test环境，重启3355，测试