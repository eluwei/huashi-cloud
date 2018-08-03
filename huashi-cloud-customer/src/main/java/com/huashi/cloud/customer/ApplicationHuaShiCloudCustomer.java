package com.huashi.cloud.customer;

import com.huashi.cloud.common.mq.rabbitmq.model.Bar;
import com.huashi.cloud.common.mq.rabbitmq.model.Foo;
import com.huashi.cloud.common.mq.rabbitmq.sender.SenderService;
import com.huashi.cloud.common.repositoryFactory.BaseRepositoryFactoryBean;
import com.huashi.cloud.customer.filter.XForwardedForFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.UUID;


@SpringBootApplication(scanBasePackages = {"com.huashi.cloud"})
@EnableEurekaClient
@EnableZuulProxy
@EnableJpaRepositories(basePackages = {"com.huashi.cloud"}, repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class ApplicationHuaShiCloudCustomer implements CommandLineRunner {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public XForwardedForFilter getIPFilter(){
        return new XForwardedForFilter();
    }

    @Autowired
    SenderService senderService;

    public static void main(String[] args) {
        new SpringApplicationBuilder(ApplicationHuaShiCloudCustomer.class).web(true).run(args);
    }

    @Override
    public void run(String... strings) throws Exception {
        Random random = new Random();
        senderService.sendBar2Rabbitmq(new Bar(random.nextInt()));
        senderService.sendFoo2Rabbitmq(new Foo(UUID.randomUUID().toString()));
    }
}