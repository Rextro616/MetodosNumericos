package com.example.Mate.models;

import net.objecthunter.exp4j.function.Function;

public class SinFunction extends Function {

    public SinFunction() {
        super("sin");
    }

    @Override
    public double apply(double... args) {
        return Math.sin(args[0]);
    }
}
