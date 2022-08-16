package com.musalasoft.drone.station;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.musalasoft.drone.station")
public class DroneStationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DroneStationApplication.class, args);
	}

}
