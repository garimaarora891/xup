package com.xebia.xup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class XupApplication {

	public static void main(String[] args) {
		SpringApplication.run(XupApplication.class, args);
	}
	
//	@Scheduled(fixedRate = 1000)
//	public void scheduleFixedRateTask() {
//	    System.out.println(
//	      "Fixed rate task - " + System.currentTimeMillis() / 1000);
//	}

}
