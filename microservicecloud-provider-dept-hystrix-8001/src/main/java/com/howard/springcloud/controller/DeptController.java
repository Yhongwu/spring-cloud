package com.howard.springcloud.controller;

import java.util.List;

import com.howard.springcloud.entities.Dept;
import com.howard.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController {
    @Autowired
    private DeptService service;
    @Autowired
    private DiscoveryClient client;

    //@RequestBody json提交表单
    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept) {
        return service.add(dept);
    }

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    //一旦调用服务方法失败并抛出了错误信息后，会自动调用@HystrixCommand标注好的fallbackMethod调用类中的指定方法
    @HystrixCommand(fallbackMethod = "processHystrixGet")
    public Dept get(@PathVariable("id") Long id) {
        Dept dept = this.service.get(id);

        if (null == dept) {
            throw new RuntimeException("该ID：" + id + "没有没有对应的信息");
        }

        return dept;
    }


    /**
     * get方法不能成功调用的处理
     * @param id
     * @return
     */
    public Dept processHystrixGet(@PathVariable("id") Long id) {
        return new Dept().setDeptNo(id).setDeptName("该ID：" + id + "没有没有对应的信息,null--@HystrixCommand")
                .setDbSource("no this database in MySQL");
    }



    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> list() {
        return service.list();
    }


    /**
     * 服务发现
     * 暴露本微服务相关信息
     * @return
     */
    @RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
    public Object discovery() {
        //获取eureka拥有的微服务
        List<String> list = client.getServices();
        System.out.println("**********" + list);

        // 获取实例名为MICROSERVICECLOUD-DEPT的微服务实例
        List<ServiceInstance> srvList = client.getInstances("MICROSERVICECLOUD-DEPT");
        for (ServiceInstance element : srvList) {
            //打出相关信息
            System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"
                    + element.getUri());
        }
        return this.client;
    }

}
