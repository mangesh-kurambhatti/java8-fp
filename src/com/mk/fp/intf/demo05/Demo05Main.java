package com.mk.fp.intf.demo05;
interface Flyable {
    default void fly() {
        System.out.println(this.getClass().getName() + " - Fly");
    }
}

interface Aeroplane extends Flyable {
//	default void fly() {
//		System.out.println(this.getClass().getName() + " - Fly above clouds.");
//	}
}

interface Bird extends Flyable {
//	default void fly() {
//		System.out.println(this.getClass().getName() + " - Fly below clouds.");
//	}
}

interface AieroplaneBird extends Aeroplane,Bird {
	default void fly() {
		System.out.println(this.getClass().getName() + " - Fly below wherever.");
	}
}

//class RealAeroplaneBird implements Aeroplane, Bird {
//}

class RealAeroplaneBird implements AieroplaneBird{
}

public class Demo05Main {
    public static void main(String[] args) {

        //Scenario-1 it will call the base interface method fly()
        new RealAeroplaneBird().fly();


        //Scenario-2
            //here 1st we will uncomment the method from @Aeroplane interface and will invoke the fly method

            Aeroplane aeroplane= new RealAeroplaneBird();
            aeroplane.fly();
            Bird bird=new RealAeroplaneBird();
            bird.fly();

            /*
                o/p of above lines will be -
                    com.mk.fp.intf.demo05.RealAeroplaneBird - Fly above clouds.
                    com.mk.fp.intf.demo05.RealAeroplaneBird - Fly above clouds.
                    com.mk.fp.intf.demo05.RealAeroplaneBird - Fly above clouds.

                That means now whatever reference we create it look for the fly() method in hierarchy
                and while traversing hierarchy it finds it in @Aeroplane interface only.

                If we uncomment method from both @Aeroplane & @Bird interfaces it will give ambiguity error
            * */

            //Scenario-3

        AieroplaneBird aieroplaneBird=new RealAeroplaneBird();
        aieroplaneBird.fly();

        /*After executing above lines we get o/p as ->
        *     'com.mk.fp.intf.demo05.RealAeroplaneBird - Fly below wherever.'
        *
        * for this we have commented @RealAeroplaneBird class and create new
        *  @RealAeroplaneBird clas which implements @AieroplaneBird
        *
        * */
    }
}

