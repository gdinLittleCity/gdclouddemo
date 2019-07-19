package com.littlecity.cloud.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.littlecity.cloud.user.*"})
@EnableEurekaClient
public class UserApplication {

		public static void main(String[] args) {
				SpringApplication.run(UserApplication.class, args);
		}

}
