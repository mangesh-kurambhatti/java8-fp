package com.mk.fp.lambda.demo02;

import java.util.function.BinaryOperator;
import java.util.function.Function;

public class Demo02Main {
    public static void main(String[] args) {
//        Function<String, Integer> f = str->str.length();
//        int res = f.apply("Sunbeam");
//        System.out.println("Length: " + res);
//        System.out.println("Class: " + f.getClass().getName());

        BinaryOperator<Integer> op1 = (a,b) -> a + b;
        callFunc(op1, 9, 5);

        int c = 2;
        BinaryOperator<Integer> op2 = (a,b) -> a + b + c;
        callFunc(op2, 9, 5);
    }
    public static void callFunc(BinaryOperator<Integer> op, int x, int y) {
        int res = op.apply(x, y);
        System.out.println("Result = " + res);
    }
}
