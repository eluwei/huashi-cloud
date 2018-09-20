package com.huashi.cloud.customer;

import com.huashi.cloud.common.repositoryFactory.BaseRepositoryFactoryBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication(scanBasePackages = {"com.huashi.cloud"})
@EnableEurekaClient
@EnableCaching
@EnableJpaRepositories(basePackages = {"com.huashi.cloud"}, repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class ApplicationHuaShiCloudCustomer implements CommandLineRunner {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }



//    @Autowired
//    SenderService senderService;

    public static void main(String[] args) {
        new SpringApplicationBuilder(ApplicationHuaShiCloudCustomer.class).web(true).run(args);
    }

    @Override
    public void run(String... strings) throws Exception {
//        Random random = new Random();
//        senderService.sendBar2Rabbitmq(new Bar(random.nextInt()));
//        senderService.sendFoo2Rabbitmq(new Foo(UUID.randomUUID().toString()));
    }
}