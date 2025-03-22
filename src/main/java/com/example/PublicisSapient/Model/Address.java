package com.example.PublicisSapient.Model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String address;
    private String city;
    private String state;
    private String stateCode;
    private String postalCode;
    private String country;

    @Embedded
    private Coordinates coordinates;
}

