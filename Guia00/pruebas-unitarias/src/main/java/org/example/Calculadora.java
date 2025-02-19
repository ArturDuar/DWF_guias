package org.example;

public class Calculadora {

    public double Suma(double number1, double number2){
        return number1 + number2;
    }

    public double Resta(double number1, double number2){
        return number1 - number2;
    }

    public double Multiplicacion(double number1, double number2){
        return number1 * number2;
    }

    public double Division(double number1, double number2){
        if (number2 != 0){
            return number1 / number2;
        } else {
            System.out.println("No se puede realizar división entre 0");
            return 0;
        }
    }
}
