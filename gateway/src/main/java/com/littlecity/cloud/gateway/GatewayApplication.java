package com.littlecity.cloud.gateway;

import com.littlecity.cloud.gateway.filter.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
public class GatewayApplication {

		public static void main(String[] args) {
				SpringApplication.run(GatewayApplication.class, args);
		}

		@Bean
		public TokenFilter tokenFilter(){
				return new TokenFilter();
		}

}





