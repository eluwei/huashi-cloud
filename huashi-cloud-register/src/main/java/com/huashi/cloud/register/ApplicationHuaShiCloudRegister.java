package com.huashi.cloud.register;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
public class ApplicationHuaShiCloudRegister {
	public static void main(String[] args) {
		new SpringApplicationBuilder(ApplicationHuaShiCloudRegister.class).web(true).run(args);
	}
}
