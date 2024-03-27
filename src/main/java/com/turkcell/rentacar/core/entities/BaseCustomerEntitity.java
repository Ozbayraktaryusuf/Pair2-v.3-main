package com.turkcell.rentacar.core.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@MappedSuperclass
public class BaseCustomerEntitity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "createdDate")
	private LocalDateTime createdDate;
	@Column(name = "updatedDate")
	private LocalDateTime updatedDate;
	@Column(name = "deletedDate")
	private LocalDateTime deletedDate;
	@Column(name = "findexScore")
	private double findexScore;
}
