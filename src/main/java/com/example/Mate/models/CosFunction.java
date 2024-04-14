package com.example.Mate.models;

import net.objecthunter.exp4j.function.Function;

public class CosFunction extends Function {

    public CosFunction() {
        super("cos");
    }

    @Override
    public double apply(double... args) {
        return Math.cos(args[0]);
    }
}
