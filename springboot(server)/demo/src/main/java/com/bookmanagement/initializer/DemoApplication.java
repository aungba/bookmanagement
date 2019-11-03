package com.bookmanagement.initializer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * This is book management application
 * User can borrow and return the book
 * Admin can add , edit and delete the book
 * @author Aung Ba
 *
 */
@MapperScan("com.bookmanagement.dao")
@ComponentScan("com.bookmanagement")
@SpringBootApplication
public class DemoApplication {
	
	/**
	 * This is main method
	 * @param args is not used
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
