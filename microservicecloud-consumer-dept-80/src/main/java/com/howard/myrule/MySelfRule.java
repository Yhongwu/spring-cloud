package com.howard.myrule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;

/**
 * 自定义算法
 * 轮询策略
 * 每个服务器被调用5次后才接着轮询下一个服务器
 */
@Configuration
public class MySelfRule {

	//还可以通过配置文件的方式配置。可参考书籍Spring Cloud与Docker微服务架构实战
	@Bean
	public IRule myRule()
	{
		//return new RandomRule();// Ribbon默认是轮询，我自定义为随机
		//return new RoundRobinRule();// Ribbon默认是轮询，我自定义为随机
		
		return new MyRandomRule();// 我自定义为每台机器5次
	}
}
