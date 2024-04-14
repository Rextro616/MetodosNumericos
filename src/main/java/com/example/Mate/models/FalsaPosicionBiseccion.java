package com.example.Mate.models;

import lombok.Data;

@Data
public class FalsaPosicionBiseccion extends MetodosNumericos {
    private double limiteA;
    private double limiteB;
    private double nuevoIntervaloMultiplicacion;

    public FalsaPosicionBiseccion(Double raíz, Double error, Double limiteA, Double limiteB, Double nuevoIntervaloMultiplicacion) {
        super(raíz, error);
        this.limiteA = limiteA;
        this.limiteB = limiteB;
        this.nuevoIntervaloMultiplicacion = nuevoIntervaloMultiplicacion;
    }
}
