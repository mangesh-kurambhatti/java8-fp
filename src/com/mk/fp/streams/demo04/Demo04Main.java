 package com.mk.fp.streams.demo04;


import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Demo04Main {
    public static void main(String[] args) {
        Integer[] nums = {23, 32, 67, 89, 45, 76, 12, 34, 56, 12, 78, 32, 54};
        //Integer[] nums = {};


        Supplier<Stream<Integer>> sup = () -> Stream.of(nums);

        /*Scenario- 1
        * These all methods are returning Optional
        * so if we print below statements it will not give us normal value
        * it will give us value with Optional[value] like our value in box
        * */

        /*Scenario-2
        * Now earlier we are creating a stream from Intger array
        * Now lets consinder line no -11 statement where array is empty
        * in that case all our max,min operations should throw some error
        * But 'Optional' saves us in handling null values
        * */
        System.out.println("Stream Max: " + sup.get().max(Integer::compareTo));

        System.out.println("Stream Min: " + sup.get().min(Integer::compareTo));

        System.out.println("Stream Sum: " + sup.get().reduce(Integer::sum));

        System.out.println("Stream Any: " + sup.get().findAny());

        System.out.println("Stream Any: " + sup.get().findFirst());

        sup.get().reduce(Integer::sum).ifPresent(x-> System.out.println("Sum is :"+x));

        class Class3 {
            class Class2 {
                class Class1 {
                    Integer val = 10;
                }
                Class1 obj1 = new Class1();
            }
            Class2 obj2 = new Class2();
        }
        /*Earlier we need to use like this if else to check null*/
        Class3 obj3 = new Class3();
        if(obj3!=null && obj3.obj2!=null && obj3.obj2.obj1!=null && obj3.obj2.obj1.val!=null)
            System.out.println("Value: " + obj3.obj2.obj1.val);

        /**/
        Optional.of(obj3)
                .flatMap(o3 -> Optional.ofNullable(o3.obj2))
                .flatMap(o2 -> Optional.ofNullable(o2.obj1))
                .flatMap(o1 -> Optional.ofNullable(o1.val))
                .ifPresent(v->System.out.println("Value: " + v));

        System.out.println("anyMatch: " + sup.get().anyMatch(i -> i>=35 && i<=40));

        System.out.println("allMatch: " + sup.get().allMatch(i -> i>=10 && i<=99));

        System.out.println("noneMatch: " + sup.get().noneMatch(i -> i>=35 && i<=40));


    }
}

