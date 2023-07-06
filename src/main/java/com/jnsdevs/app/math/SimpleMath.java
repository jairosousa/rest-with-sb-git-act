package com.jnsdevs.app.math;

/**
 * @Autor Jairo Nascimento
 * @Created 06/07/2023 - 11:52
 */
public class SimpleMath {

    public Double sum(Double numberOne, Double numberTwo) {

        return numberOne + numberTwo;
    }

    public Double subtraction(Double numberOne, Double numberTwo) {
        return numberOne - numberTwo;
    }

    public Double multiplication(Double numberOne, Double numberTwo) {

        return numberOne * numberTwo;
    }

    public Double division(Double numberOne, Double numberTwo) {

        return numberOne / numberTwo;
    }

    public Double mean(Double numberOne, Double numberTwo) {

        return (numberOne + numberTwo) / 2;
    }

    public Double squareRoot(Double number) {

        return Math.sqrt(number);
    }
}
