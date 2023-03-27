package vn.petstore.website;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class PetApp {

	public static void main(String[] args) {

		SpringApplication.run(PetApp.class, args);
	}

}
