package com.mk.fp.intf.demo01;

interface Shape{

    double PI = 3.14;
    double calArea();

    default double calPeri(){
        return 0.0;
    }
}
class Square implements Shape{

    double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public double calArea() {
        return this.side * side;
    }

    @Override
    public double calPeri() {
        return 4*this.side;
    }
}
class Circle implements Shape {

    double radius;

    public Circle(double side) {
        this.radius = side;
    }

    @Override
    public double calArea() {
        return PI * radius * radius;
    }

}

public class Demo01Main {

    public static void main(String[] args) {
        Square square= new Square(10);
        System.out.println("Area is square is :" +square.calArea());
        System.out.println("Perimeter is square is :" +square.calPeri());

        Circle circle= new Circle(10);
        System.out.println("Area of circle is :" +circle.calArea());
        System.out.println("Perimeter of circle is :" +circle.calPeri());
    }
}
