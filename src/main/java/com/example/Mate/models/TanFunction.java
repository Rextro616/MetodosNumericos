package com.example.Mate.models;

import net.objecthunter.exp4j.function.Function;

public class TanFunction extends Function {

    public TanFunction() {
        super("tan");
    }

    @Override
    public double apply(double... args) {
        return Math.tan(args[0]);
    }
}
