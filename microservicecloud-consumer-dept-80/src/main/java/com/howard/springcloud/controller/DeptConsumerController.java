package com.howard.springcloud.controller;

import com.howard.springcloud.entities.Dept;
import org.apache.el.parser.BooleanNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 微服务消费者控制类
 * 调用微服务提供者提供的接口，而不应该注重业务逻辑，即该模块不会出现service层
 * 消费者负责提供暴露接口给外部调用
 * Created by Howard Yao on 2018/5/29.
 */
@RestController
public class DeptConsumerController {

    //单机下：rest访问地址前缀 访问服务提供类的接口
    //private static final String REST_URL_PREFIX = "http://localhost:8001";
    //集群下 访问路径前缀不能写死，而是写要访问的实例名
    private static final String REST_URL_PREFIX = "http://MICROSERVICECLOUD-DEPT";

    /**
     * 在configBean中配置了该bean
     * 使用 使用restTemplate访问restful接口非常的简单粗暴无脑。 (url, requestMap,
     * ResponseBean.class)这三个参数分别代表 REST请求地址、请求参数、HTTP响应转换被转换成的对象类型。
     */
    @Autowired
    private RestTemplate template;

    @RequestMapping(value = "/consumer/dept/add")
    public Boolean add(Dept dept) {
        //post请求
        return template.postForObject(REST_URL_PREFIX+"/dept/add",dept,Boolean.class);
    }

    @RequestMapping(value = "/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        //get请求
        return template.getForObject(REST_URL_PREFIX + "/dept/get/" + id, Dept.class);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/consumer/dept/list")
    public List<Dept> list() {
        return template.getForObject(REST_URL_PREFIX + "/dept/list", List.class);
    }

    /**
     * 测试EnableDiscoveryClient 消费端可以调用服务发现
     * @return
     */
    @RequestMapping("/consumer/dept/discovery")
    public Object discovery() {
        return template.getForObject(REST_URL_PREFIX+"/dept/discovery",Object.class);
    }
}
