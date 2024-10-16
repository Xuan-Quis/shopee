package com.actvn.Shopee_BE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.actvn.Shopee_BE")
@SpringBootApplication
public class ShopeeBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopeeBeApplication.class, args);
	}

}
