package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int feedbackid;
	
	@NotNull(message = "Please enter your Username for submitting Feedback of the Maid.")
	@Size(min=3,max=25,message="Minimum 3 characters and maximum 25 characters allowed.")
    @Column(name="username",length = 25)
	@Pattern(regexp="^[a-zA-Z0-9\\s]*$",message="Only Alphabets and Numeric Values are Allowed.")
	private String username;
	
	 @Min(value = 1, message = "Rating value must be at least 1")
	 @Max(value = 5, message = "Rating value must not exceed 5")
	 private double ratings;
	
	@NotNull(message = "Please enter Maid Id for providing Feedback.")
	private Integer Maidid;

//	@NotNull(message = "Please enter your Thoughts and Feedback of the Book.")
	@Size(min=3,max=250,message="Minimum 3 characters and maximum 250 characters allowed.")
    @Column(name="review",length = 250)
	private String review;
	
    // Relationship with the maid for which feedback is provided
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
    private Maid maid;
	
    // Relationship with the user who provided the feedback
	@JsonIgnore
	@ManyToOne
	public User user;
}
