package com.yang.springcloud.controller;

import com.yang.springcloud.entities.CommonResult;
import com.yang.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import com.yang.springcloud.service.PaymentService;

import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String servertPort;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("******插入结果："+result);
        if(result>0){
            return new CommonResult(200,"插入数据库成功,serverPort:"+servertPort,result);
        }
        return new CommonResult(444,"插入数据库失败",null);
    }


    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("******查询结果："+payment);
        if(payment!=null){
            return new CommonResult(200,"查询数据库成功,serverPort:"+servertPort,payment);
        }
        return new CommonResult(444,"没有对应记录，查询ID："+id,null);
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> service = discoveryClient.getServices();
        for(String element:service){
            log.info("*******element:"+element);
        }



        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for(ServiceInstance instance:instances){
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }

        return this.discoveryClient;
    }
}
