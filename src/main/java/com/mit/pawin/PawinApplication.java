package com.mit.pawin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@ServletComponentScan
@EnableScheduling
public class PawinApplication {

	public static void main(String[] args) {
		SpringApplication.run(PawinApplication.class, args);
	}

}
