package com.robot.evoluum.br;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class RodotEvoluumApplication {

	public static void main(String[] args) {
		SpringApplication.run(RodotEvoluumApplication.class, args);
	}

}
