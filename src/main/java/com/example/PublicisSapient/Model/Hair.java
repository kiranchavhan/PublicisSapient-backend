package com.example.PublicisSapient.Model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hair {
    private String color;
    private String type;
}
