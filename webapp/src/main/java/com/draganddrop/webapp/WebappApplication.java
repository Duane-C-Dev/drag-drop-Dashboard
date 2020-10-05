package com.draganddrop.webapp;

import com.draganddrop.webapp.clients.BFFClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebappApplication.class, args);
	}

	@Bean
	@ConfigurationProperties(prefix = "app.user")
	public BFFClient widgetClient() {
		return new BFFClient();
	}

}
