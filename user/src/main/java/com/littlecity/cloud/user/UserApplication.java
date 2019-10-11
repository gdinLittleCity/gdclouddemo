package com.littlecity.cloud.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication(scanBasePackages = {"com.littlecity.cloud.user.*"})
//@EnableEurekaClient
public class UserApplication {

		public static void main(String[] args) {
				SpringApplication.run(UserApplication.class, args);
		}

}
