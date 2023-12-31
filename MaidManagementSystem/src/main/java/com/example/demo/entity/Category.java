package com.example.demo.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Category {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int category_id;

	    @NotEmpty(message="Category Name can't be Empty.")
		@Size(min=3,max=20,message="Minimum 3 and maximum 20 characters allowed.")
		@Pattern(regexp="^[a-zA-Z\\s]*$",message="Only Alphabets Allowed")
	    @Column(name="name",length = 25)
	    private String name;


	    
	    // Relationship with maids in this category
		@OneToMany(cascade = CascadeType.ALL,mappedBy = "category",fetch = FetchType.EAGER)
		@JsonIgnore
		private List<Maid> maids;
}
