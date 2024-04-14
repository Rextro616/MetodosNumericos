package com.example.Mate.models;

import lombok.Data;

@Data
public class Secante extends MetodosNumericos{
    private double xiMenos;
    private double xi;
    private double xiMas;

    public Secante(Double raíz, Double error, double xiMenos, double xi, double xiMas) {
        super(raíz, error);
        this.xiMenos = xiMenos;
        this.xi = xi;
        this.xiMas = xiMas;
    }
}
