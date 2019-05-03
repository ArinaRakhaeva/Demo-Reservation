package reservation.demo.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Reservation {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
	
	//the connection between users and machines
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	
	@Column(name="time", nullable= false, updatable= false)
    private Long time;
	@Column(name="date", nullable= false, updatable= false)
    private java.sql.Date date;
	
	@Column(name="machine",nullable=false, updatable= false)
	private Long machine;
	
	public Reservation() {
		
	}
	
	public Reservation(User user, Long time, java.sql.Date date, Long machine) {
		super();
		this.user=user;
		this.time=time;
		this.date=date;
		this.machine=machine;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	public Long getMachine() {
		return machine;
	}

	public void setMachine(Long machine) {
		this.machine = machine;
	}

	
}
