package com.flab.Pettit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PettitApplication {

	public static void main(String[] args) {
		SpringApplication.run(PettitApplication.class, args);
	}

}
