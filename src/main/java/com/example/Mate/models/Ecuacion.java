package com.example.Mate.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Ecuacion {
    private String ecuacion;
    private Integer limiteA;
    private Integer limiteB;

}
