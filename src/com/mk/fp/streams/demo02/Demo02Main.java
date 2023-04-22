package com.mk.fp.streams.demo02;


import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Demo02Main {
    public static void main(String[] args) {

        /* Scenario-1
            Here we have used reduce function which recuce the result to single value
         */
        Stream<Double> strm1 = Stream.generate(Math::random).limit(1000000);
        //generate methos uses supplier FI that we have used methos reference over here.

        Integer res1 = strm1
                .map(i -> (int)(i * 100)) //Stream<Double> 0.0-1.0  --> Stream<Integer> 0-100
                .filter(i -> i % 2 == 0) // filter even
                .reduce(0, (x,y)->x+y); // add 2 numbers ---> sum of all even numbers
        //here identity represents the valus if stream is empty it will atleast give 0;
        //see there are 2 methods of reduce which return Double or which return Optional<Double>
        //Optional<Double> is used to check null.

        System.out.println("Result: " + res1);

        /*Scenario -2
        *
        *same as above here we have done for odd numbers
        * */
        Stream<Double> strm2 = Stream.generate(Math::random).limit(1000000);
        Integer res2 = strm2
                .map(i -> (int)(i * 100))
                .filter(i -> i % 2 != 0)
                .reduce(0, (x,y)->x+y);
        System.out.println("Result: " + res2);

        /*Scenario-3
        * here as identity for reduce we have used 1 bcz we are doing multiplication
        * if we specified zero it result as 0 and in muliplication we multiply anything with 0 result will incorrect
        *
        * Also we have used iterate method to create stream
        * */
        Stream<Integer> strm3 = Stream.iterate(1, i->i+1).limit(5);
        Integer res3 = strm3
                .reduce(1, (x,y)->x*y);
        System.out.println("Factorial Result: " + res3);

        /*See book for explanation of Intstream*/
        IntStream strm4 = IntStream.rangeClosed(1, 5);
        Integer res4 = strm4
                .reduce(1, (x,y)->x*y);
        System.out.println("Factorial Result: " + res4);

        /*using arrau for creation of stream*/
        Integer[] nums = {23, 32, 67, 89, 45, 76, 12, 34, 56, 12, 78, 32, 54};

        Stream<Integer> strm5 = Stream.of(nums);
        System.out.println("Stream Count: " + strm5.count());

    }
}

