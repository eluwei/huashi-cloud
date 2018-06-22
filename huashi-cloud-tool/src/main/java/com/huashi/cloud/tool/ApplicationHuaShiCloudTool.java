package com.huashi.cloud.tool;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ApplicationHuaShiCloudTool {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ApplicationHuaShiCloudTool.class).web(true).run(args);
	}
}
