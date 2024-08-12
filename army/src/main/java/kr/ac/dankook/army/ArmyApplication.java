package kr.ac.dankook.army;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class ArmyApplication {

	public static void main(String[] args) {

		SpringApplication.run(ArmyApplication.class, args);
	}

}
