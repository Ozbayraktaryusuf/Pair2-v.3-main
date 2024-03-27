package com.turkcell.rentacar.business.dtos.requests.creates.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePaymentRequest {

    @NotNull
    @Size(min = 2,max = 45)
    private String cardHolderName;

    @NotNull
    @Pattern(regexp = "\\d{16}", message = "Credit card number must be 16 digits")
    @Size(min = 16,max = 16)
    private String cardNumber;

    @Pattern(regexp = "\\d{3}", message = "CVV must be 3 digits")
    @NotNull
    @Size(min = 3,max=3)
    private String cvv;

    @Pattern(regexp = "(0[1-9]|1[0-2])/[0-9]{2}", message = "Expiration date must be in the format MM/YY")
    @NotNull
    private String expirationDate;

    @NotNull
    private int dayOfRent;
    
    private String nationalNumber;

    private String taxIdNumber;

    @NotNull
    private int carId;
}
