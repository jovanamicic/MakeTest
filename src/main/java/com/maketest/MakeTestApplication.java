package com.maketest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
//@EnableWebMvc
public class MakeTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MakeTestApplication.class, args);
	}
}
