package com.howard.springcloud;

import com.howard.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
//@EnableDiscoveryClient 效果等同EnableEurekaClient，只是EnableEurekaClient必须是与eureka整合，而EnableDiscoveryClient不一定是使用eureka，可以使用其它服务注册组件，EnableDiscoveryClient是抽象出的注解
@EnableEurekaClient //Ribbon需要与rureka整合(也可以不与eureka整合，可参考书籍Spring Cloud与Docker微服务架构实战)
@RibbonClient(name="MICROSERVICECLOUD-DEPT",configuration=MySelfRule.class) //表示在启动该微服务时加载Ribbon配置类
public class MicroservicecloudConsumerDept80Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicecloudConsumerDept80Application.class, args);
	}
}
//启动microservicecloud-provider-dept-8001
//启动microservicecloud-consumer-dept-80
//测试： http://localhost:8001/dept/list
//http://localhost/consumer/dept/add?deptName=2018

// 测试6：测试消费端调用服务发现EnableDiscoveryClient
// 先后启动eureka-7001，dept-8001，dept-consumer-80,输入http://localhost/consumer/dept/discovery

// 测试8 : spring cloud ribbon 客户端的负载均衡
// 由于需要与eureka整合，所以pom增加ribbon与eureka相关jar
// 修改yml，新增eureka的service-url和register-with-eureka相关配置，将集群的三个url写进配置
// 由于客户端没有业务逻辑，所有的访问都是通过restTemple进行操作，所以负载均衡在注入restTemple这个bean时添加相关配置，@LoadBalanced
// 主启动类添加 @EnableEurekaClient 使得eureka可以与Ribbon整合
// 修改controller类：修改REST_URL_PREFIX
// 启动eureka-7001，7002，7003，启动dept-8001，启动dept-consumer-80
// http://localhost/consumer/dept/get/1 测试 此时是通过eureka调用微服务MICROSERVICECLOUD-DEPT 而不是写死的8001

// 测试9: spring cloud ribbon 客户端的负载均衡
// 为了更明显看出负载均衡 新建dept-8002和dept-8003 内容和dept-8001大体类似
// 修改8002和8003端口
// 新建数据库clouddb02和clouddb03，数据内容与cloudb01一致，仅以new database区分(见dept-8001的tmp.sql)
// 注意dept-8001、8002、8003的微服务实例名必须一致，这里为microservicecloud-dept
// 修改8002和8003分别连接到cloudb02和cloudb03数据库
// 修改8002和8003的instance-id以互相区分
// 自测：启动eureka-7001、7002、7003，启动dept-8001、8002、8003
// 分别输入http://localhost:8003/dept/list http://localhost:8002/dept/list http://localhost:8001/dept/list查看服务是否正常
// Ribbon 启动dept-consumer-80 这时候eureke上有三个dept，即8001、8002、8003 所以此时输入http://localhost/consumer/dept/list
// 会发现访问的数据可能来自数据库cloudb01、也可能cloudb02、cloudb03随机访问 Ribbon负载均衡成功

// 测试10：切换不同的Ribbon负载均衡算法
// Ribbon提供了7个算法的实现
// 切换方法：在configBean中配置Rule的具体算法

// 测试11：切换不同的Ribbon负载均衡算法
// 除了上面的方式切换算法外，还可以在主启动类加上：
// @RibbonClient(name="MICROSERVICECLOUD-DEPT",configuration=MySelfRule.class)
// 表示在启动该微服务时加载Ribbon配置类，此时ConfigBean的算法配置失效。以MySelfRule中的算法配置为准
// 注意：该配置不能在@componentScan扫描的包及子包下，（否则，该配置将对所有eureka-client生效，而不是针对某个服务）由于springboot默认扫描范围为主启动类的包及其子包，所以这里新建com
//.howard.myrule包。
// 除了使用提供的算法，还能自定义算法，继承AbstractLoadBalancerRule并修改其实现，如MyRandomRule
// 启动eureka7001 7002 7003 启动dept-8001 8002 8003 启动consumer-80 输入http://localhost/consumer/dept/list测试



