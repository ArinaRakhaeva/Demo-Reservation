package reservation.demo.domain;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
	List<Reservation> findAll();
	List<Reservation> findByTimeAndMachine(Long time, Long machine);
}

