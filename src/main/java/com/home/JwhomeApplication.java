package com.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableScheduling
public class JwhomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwhomeApplication.class, args);
	}

}
