package tech.techiocean.async_consumer_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class AsyncConsumer1Application {

	public static void main(String[] args) {
		SpringApplication.run(AsyncConsumer1Application.class, args);
	}

}
