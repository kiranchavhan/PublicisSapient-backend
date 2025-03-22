package com.example.PublicisSapient.Model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coordinates {
    private double lat;
    private double lng;
}

