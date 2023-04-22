package com.mk.fp.intf.demo06;
/*************************************************************************
 * Java8 Functional Programming Webinar at Sunbeam Infotech.
 * Author: Nilesh Ghule <nilesh@sunbeaminfo.com>
 * Date: 16-May-2020 & 17-May-2020
 * Demo: Interface static methods
 *************************************************************************/

interface Shape {
    double PI = 3.142;
    double calcArea();
    default double calcPeri() {
        return 0.0;
    }
    static double totalArea(Shape[] arr) {
        double sum = 0.0;
        for (Shape s : arr)
            sum += s.calcArea();
        return sum;
    }
}

class Shapes {
    public static double totalPeri(Shape[] arr) {
        double sum = 0.0;
        for (Shape s : arr)
            sum += s.calcPeri();
        return sum;
    }
}

class Square implements Shape {
    private double side;
    public Square(double side) {
        this.side = side;
    }
    @Override
    public double calcArea() {
        return side * side;
    }
    @Override
    public double calcPeri() {
        return 4 * side;
    }
}

class Circle implements Shape {
    private double radius;
    public Circle(double radius) {
        this.radius = radius;
    }
    @Override
    public double calcArea() {
        return PI * radius * radius;
    }
    @Override
    public double calcPeri() {
        return 2 * PI * radius;
    }
}

public class Demo06Main {
    public static void main(String[] args) {
        Shape[] arr = {
                new Circle(7),
                new Square(10),
                new Circle(14),
                new Square(20)
        };
        System.out.println("Total Peri: " + Shapes.totalPeri(arr));
        System.out.println("Total Area: " + Shape.totalArea(arr));
    }
}
