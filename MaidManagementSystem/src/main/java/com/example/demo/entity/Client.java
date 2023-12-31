package com.example.demo.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
@Table(name="client")
public class Client {
 	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId;
 	
 	
 	@NotEmpty(message="Publisher Name can't be Empty.")
	@Size(min=3,max=20,message="Minimum 3 and maximum 20 characters allowed.")
	@Pattern(regexp="^[a-zA-Z\\s]*$",message="Only Alphabets Allowed")
    @Column(name="name",length = 30)
 	private String name;
 	
 	@NotEmpty(message="Publisher's Address Name can't be Empty.")
	@Size(min=10,max=75,message="Minimum 10 and maximum 75 characters allowed.")
	@Pattern(regexp="^[a-zA-Z0-9\\s]*$",message="Only Alphabets Allowed")
    @Column(name="address",length = 80)
 	@Embedded
 	private Address address;
 	
 	@NotEmpty(message = "please enter contactno ")
 	@Size(min=10,max=10,message="Please Enter proper Contact number")
	@Pattern(regexp="^\\d{10}$",message="Only 10 Numbers Allowed")
    @Column(name="DeliveryStatus",length = 12)
    private String contactNumber;
    
    // Relationship with maids booked by this client
    @OneToMany(mappedBy = "client")
    @JsonIgnore
	private List<Maid> maids;
}
