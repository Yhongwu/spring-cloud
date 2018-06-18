package com.howard.springcloud.controller;

import com.howard.springcloud.entities.Dept;
import com.howard.springcloud.service.DeptClientService;
import org.apache.el.parser.BooleanNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Feign测试
 * Created by Howard Yao on 2018/5/29.
 */
@RestController
public class DeptConsumerController {

    //单机下：rest访问地址前缀 访问服务提供类的接口
    //private static final String REST_URL_PREFIX = "http://localhost:8001";
    //集群下 访问路径前缀不能写死，而是写要访问的实例名
    private static final String REST_URL_PREFIX = "http://MICROSERVICECLOUD-DEPT";


    @Autowired
    private DeptClientService service;

    @RequestMapping(value = "/consumer/dept/add")
    public Boolean add(Dept dept) {
        return service.add(dept);
    }

    @RequestMapping(value = "/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        //get请求
        return service.get(id);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/consumer/dept/list")
    public List<Dept> list() {
        return service.list();
    }

}
