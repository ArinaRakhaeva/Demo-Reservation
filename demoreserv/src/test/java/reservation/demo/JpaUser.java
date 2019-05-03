package reservation.demo;

import static org.assertj.core.api.Assertions.*;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import reservation.demo.domain.Reservation;
import reservation.demo.domain.ReservationRepository;
import reservation.demo.domain.User;
import reservation.demo.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaUser {
	
	@Autowired
	private UserRepository urepository;
	
	@Autowired
	private ReservationRepository rrepository;
	
	@Test
	public void notExistedUser() {
		User user = urepository.findByUsername("NotExistedUser");
		assertThat(user).isNull();
	}
	
	@Test
	public void createUser() {
		User user = new User("testUser", "$2a$10$AhxTY7sngNMrykd..siZZeDqTPRVBCwrT76m.a7qdunEsOfaDWsEO", "USER");
		urepository.save(user);
		user = urepository.findByUsername("testUser");
		assertThat(user).isNotNull();
	}
	
	@Test
	public void createReservation() {
		User user = new User("testUser", "$2a$10$AhxTY7sngNMrykd..siZZeDqTPRVBCwrT76m.a7qdunEsOfaDWsEO", "USER");
		urepository.save(user);
		Reservation res = new Reservation(user, new Long(2), new java.sql.Date(1235), new Long(2));
		rrepository.save(res);
		List<Reservation> reservations = rrepository.findByTimeAndMachine(new Long(2), new Long(2));
		assertThat(reservations).hasSize(1);
	}
}
