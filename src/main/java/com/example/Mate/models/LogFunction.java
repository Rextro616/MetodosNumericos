package com.example.Mate.models;

import net.objecthunter.exp4j.function.Function;

public class LogFunction extends Function {

    public LogFunction() {
        super("log");
    }

    @Override
    public double apply(double... args) {
        return Math.log(args[0]);
    }
}

