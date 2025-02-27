package com.example.coffeeshop.adapter.out.persistence.entity;

import com.example.coffeeshop.application.models.CreditCard;
import com.example.coffeeshop.application.models.Payment;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    private UUID orderId;

    @NotNull
    private String cardHolderName;

    @NotNull
    private String cardNumber;

    @NotNull
    private Month expiryMonth;

    @NotNull
    private Year expiryYear;

    @NotNull
    private LocalDate paid;

    public Payment toDomain() {
        return new Payment(
                orderId,
                new CreditCard(
                        cardHolderName,
                        cardNumber,
                        expiryMonth,
                        expiryYear
                ),
                paid
        );
    }

    public static PaymentEntity fromDomain(Payment payment) {
        return PaymentEntity.builder()
                .orderId(payment.orderId())
                .cardHolderName(payment.creditCard().cardHolderName())
                .cardNumber(payment.creditCard().cardNumber())
                .expiryMonth(payment.creditCard().expiryMonth())
                .expiryYear(payment.creditCard().expiryYear())
                .paid(payment.paid())
                .build();
    }
}
