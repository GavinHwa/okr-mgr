package com.cmb.okr.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.cmb.okr.frame.EnableOkrFrame;

@EnableScheduling
@SpringBootApplication
@ComponentScan({ "com.cmb.okr.api.controller", "com.cmb.okr.api.conf", "com.cmb.okr.service",
		"com.cmb.okr.attachment" })
@EnableOkrFrame
public class OkrApp {

	public static void main(String[] args) {
		SpringApplication.run(OkrApp.class, args);
	}
}
