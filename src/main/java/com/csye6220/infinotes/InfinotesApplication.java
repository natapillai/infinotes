package com.csye6220.infinotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class InfinotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfinotesApplication.class, args);
	}

}
