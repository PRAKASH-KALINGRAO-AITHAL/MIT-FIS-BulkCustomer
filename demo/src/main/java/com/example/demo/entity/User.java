package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	 public User() {
	    }

	
	public User(Long userId, String name, String email) {
        this.Id = userId;
        this.name = name;
        this.email = email;
        this.user_id= 0L;
    }
    public Long getUserId() {
		return Id;
	}
	public void setUserId(Long userId) {
		this.Id = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getUser_id() {
		return user_id;
	}


	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	@Id
    private Long Id; // Using user ID as a primary key
	private Long user_id;
    private String name;
    private String email;
}
