package com.cmb.okr.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.cmb.okr.api", "com.cmb.okr.frame", "com.cmb.okr.service", "com.cmb.okr.attachment" })
public class OkrApp {

	public static void main(String[] args) {
		SpringApplication.run(OkrApp.class, args);
	}
}
