package com.yang.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//自定义ribbon的负载均衡策略。不要放在@ComponentScan能扫到的目录下
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule(){
        //定义为随机顺序负载均衡
        return new RandomRule();
    }
}
