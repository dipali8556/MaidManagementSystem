package com.example.demo.entity;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "User")
@Component
@Builder
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotEmpty(message = "username can't be empty")
	@Pattern(regexp="^[a-zA-Z0-9\\s]*$",message="Only Alphabets and Numeric Values are Allowed.")
	@Column(name = "username",length = 20)
	@Size(min=3,max=20,message="Minimum 3 and maximum 20 characters allowed.")
	private String username;
	
	@NotEmpty(message = "userpassword can't be empty")
	private String password;									
	
	@NotEmpty(message = "user email can't be empty")
	@Email(message = "Please Enter Appropriate Email Id")
	@Column(name = "email",length = 40)
	private String email;
	
	@NotEmpty(message="first Name can't be Empty.")
	@Size(min=3,max=20,message="Minimum 3 and maximum 20 characters allowed.")
	@Pattern(regexp="^[a-zA-Z\\s]*$",message="Only Alphabets Allowed")
	@Column(name = "firstname",length = 20)
    private String firstName;
	
	@NotBlank(message="Last Name can't be Empty.")
	@Size(min=3,max=20,message="Minimum 3 and maximum 20 characters allowed.")
	@Pattern(regexp="^[a-zA-Z\\s]*$",message="Only Alphabets Allowed.")
	@Column(name = "lastname",length = 20)
    private String lastName;
	
	@NotEmpty(message="Address can't be Empty.")
	@Size(min = 10,max = 80,message="Minimum 10 and maximum 80 characters allowed.")
	@Column(name = "address",length = 80)
	@Embedded
	Address address;

    // Set of roles associated with the user
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	Set<Role> roles;
	
    // List of feedback provided by the user
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
	@JsonIgnore
	List<Feedback> feedbacks;
	
    // Method to add a role to the user
	public void addRole(Role string) {
        this.roles.add(string);
}
}
