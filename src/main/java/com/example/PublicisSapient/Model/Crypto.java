package com.example.PublicisSapient.Model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Crypto {
    private String coin;
    private String wallet;
    private String network;
}

