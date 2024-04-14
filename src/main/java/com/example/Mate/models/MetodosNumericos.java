package com.example.Mate.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class MetodosNumericos {
    private double raíz;
    private double error;

    public MetodosNumericos(Double raíz, Double error) {
        this.raíz = raíz;
        this.error = error;
    }
}
