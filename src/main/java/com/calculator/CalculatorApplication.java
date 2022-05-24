package com.calculator;

import java.util.stream.IntStream;

public class CalculatorApplication {

    static IAddition addition = (x,y) -> x+y;

    static ISubtraction subtraction = (x, y) -> x-y;

    static IMultiplication multiplication = (x,y) -> IntStream.iterate(y < 0 && x < 0 ? -Math.min(x,y) : Math.min(x,y), i -> i)
            .limit(Math.abs(Math.max(x,y)))
            .reduce((cummulative, num) -> addition.calculateAddition(cummulative,num))
            .getAsInt();

    static IDivision division = (x,y) -> IntStream.iterate(x, i -> i-y)
            .limit(2)
            .reduce((cummulative, num) -> subtraction.calculateSubtraction(cummulative,num))
            .getAsInt();


    public static void main(String[] args) {
        // Executing
        System.out.println("Addition: " + addition.calculateAddition(5,2));

        System.out.println("Subtraction: " + subtraction.calculateSubtraction(5,2));

        System.out.println("Multiplication: " + multiplication.calculateMultiplication(5,-7));

        System.out.println("Division: " + division.calculateDivision(10,2));
    }
}
