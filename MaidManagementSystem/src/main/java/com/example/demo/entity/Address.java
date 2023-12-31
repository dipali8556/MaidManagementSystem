package com.example.demo.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {
    @Column(name = "Street", length = 50)
    @Size(max = 50, message = "Street length must be at most 50 characters")
    //@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Only Alphabets Allowed")
	private String street;
 
    @Column(name = "City", length = 50)
    @Size(max = 50, message = "City length must be at most 50 characters")
   // @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Only Alphabets Allowed")	
	private String city;
	
    @Column(name = "State", length = 50)
    @Size(max = 50, message = "State length must be at most 50 characters")
   // @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Only Alphabets Allowed")
    private String state;

    @Column(name = "Country", length = 50)
    @Size(max = 50, message = "Country length must be at most 50 characters")
   // @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Only Alphabets Allowed")
	private String country;
	
    @Column(name = "Pin_Code", length = 6)
    @Size(max = 6, message = "Pin Code length must be at most 6 characters")
   // @Pattern(regexp = "^\\d{6}$", message = "Only 6 Numbers Allowed")
	private String pinCode;
}
