package com.huashi.cloud.tool;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ApplicationHuaShiCloudTool {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ApplicationHuaShiCloudTool.class).web(true).run(args);
	}
}
