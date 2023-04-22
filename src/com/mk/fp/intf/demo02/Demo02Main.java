package com.mk.fp.intf.demo02;


interface Displayable {
    default void show() {
        System.out.println("Displayble show().");
    }
}

interface Printable {
    default void show() {
        System.out.println("Printable show().");
    }
}

//will cause the ambiguity error
//class FirstClass implements Displayable, Printable {
//}

class SecondClass implements Displayable, Printable {
    public void show() {
        System.out.println("SecondClass show().");
    }
}

class ThirdClass implements Displayable {
}

class FourthClass implements Printable {
}

class FifthClass implements Displayable, Printable {
    public void show() {
        //show(); // recursion
        //super.show(); // error - no super class
        System.out.print("Calling Displayable: ");
        Displayable.super.show();
        System.out.print("Calling Printable: ");
        Printable.super.show();
    }
}

public class Demo02Main {
    public static void main(String[] args) {

        // 1st scenario
        new SecondClass().show();

        // 2nd scenario
        Displayable displayable= new SecondClass();
        displayable.show();
        Printable printable=new SecondClass();
        printable.show();

        // 3rd scenario will call default method
        new ThirdClass().show();
        new FourthClass().show();

        // 4th scenario
        // <interface-name>.<super-keyword>.<method-name>
        new FifthClass().show();
    }
}
