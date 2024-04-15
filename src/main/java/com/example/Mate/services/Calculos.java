package com.example.Mate.services;

import com.example.Mate.models.*;
import lombok.AllArgsConstructor;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.apache.commons.math3.analysis.function.Cos;
import org.apache.commons.math3.analysis.function.Log10;
import org.apache.commons.math3.analysis.function.Sin;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.function.Function;

@AllArgsConstructor
@Service
public class Calculos {
    public ArrayList<ArrayList<MetodosNumericos>> respuesta(Ecuacion ecuacion) {
        try {
            Function<Double, Double> funcion = (x) -> evaluarEcuacion(ecuacion.getEcuacion(),x);
            double a = ecuacion.getLimiteA();
            double b = ecuacion.getLimiteB();
            double tolerancia = 0.001;

            Derivadas derivadas = new Derivadas();
            derivadas.setFuncionADerivar(ecuacion.getEcuacion());
            derivadas.derivar();

            String ecuacionDerivada = derivadas.getFuncionDerivada();
            Function<Double, Double> funcionDerivada = (x) -> evaluarEcuacion(ecuacionDerivada,x);

            ArrayList<MetodosNumericos> biseccion = biseccion(funcion, a, b, tolerancia);
            ArrayList<MetodosNumericos> falsaPosicion = falsaPosicion(funcion, a, b, tolerancia);
            ArrayList<MetodosNumericos> secante = secante(funcion, a, b, tolerancia);
            ArrayList<MetodosNumericos> newtonRaphson = newtonRaphson(funcion, funcionDerivada, a, b, tolerancia, ecuacionDerivada);

            ArrayList<ArrayList<MetodosNumericos>> respuesta = new ArrayList<>();
            respuesta.add(biseccion);
            respuesta.add(falsaPosicion);
            respuesta.add(secante);
            respuesta.add(newtonRaphson);

            return (respuesta);

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<MetodosNumericos> falsaPosicion(Function<Double, Double> funcion, double a, double b, double tolerancia) {
        double xa = a;
        double xb = b;
        double raiz = 100;
        double raizNueva = 100;
        double error = 0;

        ArrayList<MetodosNumericos> falsaPosicionArray = new ArrayList<>();

        do {
            raiz = raizNueva;
            raizNueva = (xa * funcion.apply(xb) - xb * funcion.apply(xa)) / (funcion.apply(xb) - funcion.apply(xa));
            double multiplicacion = funcion.apply(raizNueva) * funcion.apply(xa);

            if (funcion.apply(raizNueva) == 0.0) {
                FalsaPosicionBiseccion falsaPosicion = new FalsaPosicionBiseccion(raizNueva,error,xa,xb,multiplicacion);
                falsaPosicionArray.add(falsaPosicion);
                break;
            } else if (multiplicacion < 0) {
                xb = raizNueva;
            } else {
                xa = raizNueva;
            }

            error = Math.abs(raiz - raizNueva);

            FalsaPosicionBiseccion falsaPosicion = new FalsaPosicionBiseccion(raizNueva,error,xa,xb,multiplicacion);

            falsaPosicionArray.add(falsaPosicion);

        } while (error >= tolerancia);

        return falsaPosicionArray;
    }

    public ArrayList<MetodosNumericos> biseccion(Function<Double, Double> funcion, double a, double b, double tolerancia) {
        double xa = a;
        double xb = b;
        double raizNueva = 100;
        double error = 0;

        ArrayList<MetodosNumericos> biseccion = new ArrayList<>();

        do {
            raizNueva = (xa + xb) / 2;
            double multiplicacion = funcion.apply(raizNueva) * funcion.apply(xa);

            if (funcion.apply(raizNueva) == 0.0) {
                FalsaPosicionBiseccion falsaPosicion = new FalsaPosicionBiseccion(raizNueva,error,xa,xb,multiplicacion);
                biseccion.add(falsaPosicion);
                break;
            } else if (multiplicacion < 0) {
                xb = raizNueva;
            } else {
                xa = raizNueva;
            }

            error = Math.abs((xb - xa)/2);

            FalsaPosicionBiseccion falsaPosicion = new FalsaPosicionBiseccion(raizNueva,error,xa,xb,multiplicacion);

            biseccion.add(falsaPosicion);

        } while (error >= tolerancia);

        return biseccion;
    }

    public ArrayList<MetodosNumericos> secante(Function<Double, Double> funcion, double a, double b, double tolerancia) {
        double xiMenos = a;
        double xi = b;
        double xiMas;
        double error = 0;

        ArrayList<MetodosNumericos> secante = new ArrayList<>();

        do {
            xiMas = xi - (funcion.apply(xi) * (xiMenos - xi)) / (funcion.apply(xiMenos) - funcion.apply(xi));

            error = Math.abs((xiMas - xi)/xiMas);

            Secante secanteEc = new Secante(xi,error,xiMenos,xi,xiMas);
            secante.add(secanteEc);

            xiMenos = xi;
            xi = xiMas;

        } while (error >= tolerancia);

        return secante;
    }

    public ArrayList<MetodosNumericos> newtonRaphson(Function<Double, Double> funcion, Function<Double, Double> derivada, double a, double b, double tolerancia, String derivadaString) {
        double xi = a;
        double xiMas;
        double error = 0;

        ArrayList<MetodosNumericos> newtonRaphson = new ArrayList<>();

        do {
            xiMas = xi - (funcion.apply(xi)/ derivada.apply(xi));

            error = Math.abs((xiMas - xi)/xiMas);

            NewtonRaphson newtonRaphson1 = new NewtonRaphson(xiMas,error,xi,xiMas,derivadaString);
            newtonRaphson.add(newtonRaphson1);

            xi = xiMas;

        } while (error >= tolerancia);

        return newtonRaphson;
    }

    public double evaluarEcuacion(String ecuacion, double x) {
        try {
            Expression expresion = new ExpressionBuilder(ecuacion)
                    .variables("x")
                    .function(new LogFunction())
                    .function(new SinFunction())
                    .function(new CosFunction())
                    .function(new TanFunction())
                    .build()
                    .setVariable("x", x);
            return expresion.evaluate();
        } catch (Exception e) {
            System.err.println("Error al evaluar la ecuación: " + e.getMessage());
            return Double.NaN;
        }
    }


}
