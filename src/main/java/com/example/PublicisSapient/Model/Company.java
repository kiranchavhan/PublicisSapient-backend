package com.example.PublicisSapient.Model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    private String department;
    private String name;
    private String title;

    @Embedded
    private Address address;
}

