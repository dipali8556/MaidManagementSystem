package com.example.demo.entity;


import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
@Table(name = "Roles")

public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int rid;
	@NotEmpty(message = "role can't be empty")
	private String rname;
}
