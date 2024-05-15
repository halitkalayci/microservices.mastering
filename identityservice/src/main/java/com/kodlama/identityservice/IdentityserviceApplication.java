package com.kodlama.identityservice;

import com.kodlamaio.core.annotation.EnableCore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication()
@EnableCore()
public class IdentityserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(IdentityserviceApplication.class, args);
	}

}
