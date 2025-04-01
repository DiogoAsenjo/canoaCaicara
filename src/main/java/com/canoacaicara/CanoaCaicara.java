package com.canoacaicara;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@Slf4j
public class CanoaCaicara {

	public static void main(String[] args) {
		SpringApplication.run(CanoaCaicara.class, args);
		log.info("Application running");
	}
}
