package com.howard.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 *
 * Created by Howard Yao on 2018/5/29.
 */
@Configuration
public class ConfigBean {

    /**
     * RestTemplate提供了多种便捷访问远程http服务的方法
     * 是一种简单便捷的restful服务模板类，是spring提供的用于访问rest服务的客户端模板工具类
     * @return
     */
    @Bean
    @LoadBalanced //LB 开启spring cloud Ribbon客户端 负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 修改Ribbon轮询的算法
     * @return
     */
    @Bean
    public IRule myRule() {
        //轮询算法
        //return new RoundRobinRule();
        //随机算法
        //return new RandomRule();
        //RetryRule
        //先按照RoundRobinRule的策略获取服务，如果获取的服务失败则在指定的时间内会进行重试，获取可用的服务，如果多次碰壁，就不会再轮询那个坏掉的服务
        return new RetryRule();

        //自定义算法

    }
}
