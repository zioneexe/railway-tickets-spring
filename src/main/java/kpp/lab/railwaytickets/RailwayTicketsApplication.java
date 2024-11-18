package kpp.lab.railwaytickets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class RailwayTicketsApplication {

	public static final Logger LOGGER = LoggerFactory.getLogger(RailwayTicketsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RailwayTicketsApplication.class, args);
	}

}
