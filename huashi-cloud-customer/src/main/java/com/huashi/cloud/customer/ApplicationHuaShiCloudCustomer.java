package com.huashi.cloud.customer;

import com.huashi.cloud.common.filter.XForwardedForFilter;
import com.huashi.cloud.common.repositoryFactory.BaseRepositoryFactoryBean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication(scanBasePackages = {"com.huashi.cloud"})
@EnableEurekaClient
@EnableZuulProxy
@EnableJpaRepositories(basePackages = {"com.huashi.cloud"}, repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class ApplicationHuaShiCloudCustomer {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public XForwardedForFilter getIPFilter(){
        return new XForwardedForFilter();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(ApplicationHuaShiCloudCustomer.class).web(true).run(args);
    }
}