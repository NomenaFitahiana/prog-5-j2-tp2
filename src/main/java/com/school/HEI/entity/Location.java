package com.school.HEI.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Location {
    private UUID id;
    private LocalDate locationDate;
    private LocalDate dueDate;
    private LocalDate returnDate = null;
    private LocationState state;
    private double remainingAmount = 0.0;
    private Renter renter;
    private Rented rentedProperty;

    
    private List<Payment> payments = new ArrayList<>();

    @JsonSetter(nulls = Nulls.AS_EMPTY)
    public void setPayments(List<Payment> payments) {
        this.payments = payments != null ? payments : new ArrayList<>();
    }

    public void changeStatus() {
        if (returnDate != null && remainingAmount == 0) {
            state = LocationState.RETURNED;
        } else if (LocalDate.now().isAfter(dueDate) && returnDate == null) {
            state = LocationState.LATE;
        } else if (returnDate == null) {
            state = LocationState.TAKEN;
        }
    }

    public double getTotalAmount() {
        if (locationDate == null || dueDate == null || rentedProperty == null) {
            return 0;
        }

        long days = ChronoUnit.DAYS.between(locationDate, dueDate);

        if (days <= 0) {
            days = 1;
        }

        return days * rentedProperty.getValue();
    }

    public void initAmount() {
        this.remainingAmount = getTotalAmount();
    }

    public void pay(double amount) {
        if (remainingAmount == 0) {
            initAmount();
        }

        remainingAmount = Math.max(remainingAmount - amount, 0);
        payments.add(new Payment(LocalDate.now(), amount));

        if (remainingAmount == 0 && returnDate != null) {
            state = LocationState.RETURNED;
        }
    }


    public void returnItem() {
        this.returnDate = LocalDate.now();

        if (rentedProperty != null) {
            rentedProperty.setAvailable(true);
        }

        changeStatus();
    }
}
