package com.example.EpamSpringBoot;

 import io.swagger.v3.oas.annotations.OpenAPIDefinition;
 import io.swagger.v3.oas.annotations.info.Info;
 import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Your API", version = "1.0", description = "Your API description"))

public class EpamSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(EpamSpringBootApplication.class, args);
	}

}
