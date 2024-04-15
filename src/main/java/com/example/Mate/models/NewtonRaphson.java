package com.example.Mate.models;

import lombok.Data;
import org.nfunk.jep.function.Str;

import java.util.function.Function;

@Data
public class NewtonRaphson extends MetodosNumericos{
    private double xi;
    private double xiMas;
    private String derivada;

    public NewtonRaphson(Double raíz, Double error, double xi, double xiMas, String derivada) {
        super(raíz, error);
        this.xi = xi;
        this.xiMas = xiMas;
        this.derivada = derivada;
    }
}
