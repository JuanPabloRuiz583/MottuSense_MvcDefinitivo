package br.com.fiap.Mottusense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MottusenseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MottusenseApplication.class, args);
	}

}
