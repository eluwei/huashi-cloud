package com.huashi.cloud.customer;

import com.huashi.cloud.common.repositoryFactory.BaseRepositoryFactoryBean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = {"com.huashi.cloud"})
@EnableEurekaClient
@EnableJpaRepositories(basePackages = {"com.huashi.cloud"}, repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class ApplicationHuaShiCloudCustomer {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ApplicationHuaShiCloudCustomer.class).web(true).run(args);
    }
}