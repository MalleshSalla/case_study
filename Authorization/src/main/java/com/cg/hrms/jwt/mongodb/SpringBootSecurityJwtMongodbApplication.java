package com.cg.hrms.jwt.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration (exclude = { DataSourceAutoConfiguration.class })
//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
@EnableFeignClients
//@ComponentScan(basePackages = {"com.cg.hrms.jwt.mongodb.restclient"})
public class SpringBootSecurityJwtMongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityJwtMongodbApplication.class, args);
	}

}
