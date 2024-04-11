package com.anything;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@SpringBootApplication
@EnableScheduling
@ConfigurationPropertiesScan
public class AnythingApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnythingApplication.class, args);
	}

}
