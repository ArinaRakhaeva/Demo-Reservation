package reservation.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import reservation.demo.domain.User;
import reservation.demo.domain.UserRepository;

@SpringBootApplication

public class DemoApplication {
	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner machinesDemo( UserRepository urepository) {
		return (args) -> {
			
			// Create users: admin/admin user/user
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$AhxTY7sngNMrykd..siZZeDqTPRVBCwrT76m.a7qdunEsOfaDWsEO", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
		};
	}
}
