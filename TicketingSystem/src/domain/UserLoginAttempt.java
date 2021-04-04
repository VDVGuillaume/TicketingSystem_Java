package domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Table(name="UserLoginAttempts")
public class UserLoginAttempt implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	public int id;
	@Column(name = "Date")
	public String date;
	@Column(name = "Username")
	public String username;
	@Column(name = "Success")
	public boolean success;
	
	public UserLoginAttempt(String date, String username, boolean success) {
		this.date = date;
		this.username = username;
		this.success = success;
	}
}