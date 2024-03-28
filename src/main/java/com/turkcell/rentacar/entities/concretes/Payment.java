package com.turkcell.rentacar.entities.concretes;

import com.turkcell.rentacar.core.entities.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "payments")
public class Payment extends BaseEntity {

    @Column(name = "cardHolderName")
    private String cardHolderName;

    @Column(name = "cardNumber")
    private String cardNumber;

    @Pattern(regexp = "\\d{3}", message = "CVV must be 3 digits")
    @Column(name = "cvv")
    private String cvv;

    @Pattern(regexp = "(0[1-9]|1[0-2])/[0-9]{2}", message = "Expiration date must be in the format MM/YY")
    @Column(name = "expirationDate")
    private String expirationDate;

    @Column(name="amounts")
    private double totalAmount=0;

    @Column(name="dayOfRent")
    private int dayOfRent;

    @Column(name = "paymentState")
    private String paymentState;

    @OneToMany(mappedBy = "payment")
    private List<Rental> rental;

    @ManyToOne
    @JoinColumn(name = "extraServiceId")
    private ExtraServices extraServices;

    @ManyToOne
    @JoinColumn(name = "carId")
    private Car car;

    @Null
    @ManyToOne
    @JoinColumn(name = "personalCustomerId")
    private PersonalCustomer personalCustomer;


    @ManyToOne
    @JoinColumn(name = "corporateCustomerId")
    private CorporateCustomer corporateCustomer;
}
