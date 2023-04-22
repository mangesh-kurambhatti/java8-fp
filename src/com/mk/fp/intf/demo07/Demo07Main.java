package com.mk.fp.intf.demo07;

@FunctionalInterface
interface  IFunctional1 {
    void fun1();
}

@FunctionalInterface
interface IFunctional2 {
    void fun1();
    default void fun2() {
        System.out.println("Default implementation: IFunctional2.fun2()");
    }
}

@FunctionalInterface
interface IFunctional3 {
    void fun1();
    default void fun2() {
        System.out.println("Default implementation: IFunctional3.fun2()");
    }
    static void callFunc(IFunctional3 obj) {
        obj.fun1();
        obj.fun2();
    }
}

//@FunctionalInterface
interface IFunctional4 {
    void fun1();
    @Override String toString();
}

/*
//@FunctionalInterface
interface IFunctional5 {
	void fun1();
	default String toString() { // error: default method cannot override Object class method.
		System.out.println("Default toString()");
	}
}
*/

//@FunctionalInterface
interface INonFunctional {
    void fun1();
    void fun2();
}

class Class1 implements IFunctional1 {
    @Override
    public void fun1() {
        System.out.println("Class1.fun1() called.");
    }
}

class Class2A implements IFunctional2 {
    @Override
    public void fun1() {
        System.out.println("Class2A.fun1() called.");
    }
}

class Class2B implements IFunctional2 {
    @Override
    public void fun1() {
        System.out.println("Class2B.fun1() called.");
    }
    @Override
    public void fun2() {
        System.out.println("Class2B.fun2() called.");
    }
}

class Class3 implements IFunctional3 {
    @Override
    public void fun1() {
        System.out.println("Class3.fun1() called.");
    }
}


public class Demo07Main {
    public static void main(String[] args) {
        IFunctional3 obj = new Class3();
        IFunctional3.callFunc(obj);
    }
}
