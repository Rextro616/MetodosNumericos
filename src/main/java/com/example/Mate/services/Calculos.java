package com.example.Mate.services;

import com.example.Mate.models.Ecuacion;
import lombok.AllArgsConstructor;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Service;

import javax.script.ScriptException;
import java.util.function.Function;

@AllArgsConstructor
@Service
public class Calculos {
    public void respuesta(Ecuacion ecuacion) {
        try {
            Function<Double, Double> funcion = (x) -> x*x-20.18;

            double a = ecuacion.getLimiteA();
            double b = ecuacion.getLimiteB();

            double tolerancia = 0.01;

            double raiz = falsaPosicion(funcion, a, b, tolerancia);

            System.out.println("La raíz es aproximadamente: " + raiz);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public double falsaPosicion(Function<Double, Double> funcion, double a, double b, double tolerancia) {
        double xa = a;
        double xb = b;
        double raiz = 0;
        double raizVieja = 0;
        int iteracion = 1;

        while (true) {

            raiz = (xa * funcion.apply(xb) - xb * funcion.apply(xa)) / (funcion.apply(xb) - funcion.apply(xa));

            if (funcion.apply(raiz) == 0.0) {
                break;
            } else if (funcion.apply(raiz) * funcion.apply(xa) < 0) {
                xb = raiz;
            } else {
                xa = raiz;
            }

            iteracion++;
        }

        return raiz;
    }

    public static double evaluarEcuacion(String ecuacion, double x) {
        try {
            Expression expresion = new ExpressionBuilder(ecuacion)
                    .variables("x")
                    .build()
                    .setVariable("x", x);
            return expresion.evaluate();
        } catch (Exception e) {
            System.err.println("Error al evaluar la ecuación: " + e.getMessage());
            return Double.NaN;
        }
    }

}
