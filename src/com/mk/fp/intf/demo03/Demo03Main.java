package com.mk.fp.intf.demo03;

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

class SuperClass implements Displayable {
   @Override
    public void show() {
        System.out.println("S   ()");
    }
}

class SubClass extends SuperClass implements Printable {
}

public class Demo03Main {
    public static void main(String[] args) {
        new SuperClass().show();
        new SubClass().show();
    }
}
