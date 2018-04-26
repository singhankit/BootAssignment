package com.assignment;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:configuration.properties")
public class BootAssignmentApplication {
	public static void main(String[] args) {
		Logger logger = LogManager.getLogger(BootAssignmentApplication.class);
		logger.debug("Starting Boot Assignment Application");
		SpringApplication.run(BootAssignmentApplication.class, args);
	}
}
