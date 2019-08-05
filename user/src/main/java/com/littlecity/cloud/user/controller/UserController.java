package com.littlecity.cloud.user.controller;

import com.littlecity.cloud.user.dto.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author Joey.huang
 * @date 2019/7/19
 */
@RestController("/user")
public class UserController {

		@GetMapping
		public String hello(){
				return "hello";
		}


		@PostMapping("login")
		public String login(@RequestParam User user){


			return "";
		}

}
