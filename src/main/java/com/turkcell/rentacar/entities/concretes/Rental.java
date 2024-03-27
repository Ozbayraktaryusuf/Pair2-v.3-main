package com.turkcell.rentacar.entities.concretes;

import com.turkcell.rentacar.core.entities.BaseEntity;
import com.turkcell.rentacar.core.entities.OperationEntitiy;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "rentals")
public class Rental extends OperationEntitiy {

    @ManyToOne
    @JoinColumn(name = "carId")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "personalCustomerId")
    private PersonalCustomer personalCustomer;

    @ManyToOne
    @JoinColumn(name = "corporateCustomerId")
    private CorporateCustomer corporateCustomer;

    @ManyToOne
    @JoinColumn(name = "paymentId")
    private Payment payment;
}
