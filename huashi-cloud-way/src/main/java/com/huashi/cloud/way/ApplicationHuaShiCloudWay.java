package com.huashi.cloud.way;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import com.huashi.cloud.way.filter.XForwardedForFilter;

@EnableDiscoveryClient
@SpringBootApplication
@EnableZuulProxy
public class ApplicationHuaShiCloudWay {

	@Bean
	public XForwardedForFilter getIPFilter(){
		return new XForwardedForFilter();
	}


	public static void main(String[] args) {
		new SpringApplicationBuilder(ApplicationHuaShiCloudWay.class).web(true).run(args);
	}
}
