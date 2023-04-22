package com.mk.fp.intf.demo04;

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
        System.out.println("SuperClass show()");
    }
}

class SubClass extends SuperClass implements Printable {
}

public class Demo04Main {
    public static void main(String[] args) {
        new SuperClass().show();
        new SubClass().show();
    }
}
