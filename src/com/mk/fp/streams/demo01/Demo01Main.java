package com.mk.fp.streams.demo01;


import java.util.stream.Stream;

public class Demo01Main {
    public static void main(String[] args) {
        Integer[] nums = {23, 32, 67, 89, 45, 76, 12, 34, 56, 12, 78, 32, 54};
//        Stream<Integer> strm1 = Stream.of(nums);
//        strm1
//                .map(i -> i * i)        //square each numbers
//                .filter(i -> i % 2 == 0)  //filter even numbers
//                .sorted((x, y) -> y - x) //sort desc
//                .map(i -> Integer.valueOf(new StringBuilder("" + i).reverse().toString())) // revers number
//                .filter(i -> i % 2 != 0)  //filter odd number
//                //.distinct()
//                .forEach(System.out::println); // printing with terminal
//        System.out.println();

        /*Here we have created our own stream just like stream 1
         * We have typed it
         * once we done this question how its executing internally
         * is it completing one operation completely first like mapping,filtering,sorting etc.
         * */
//        Stream<Integer> stream0 = Stream.of(nums);
//
//        stream0
//                .map(i -> i * i)
//                .filter(i -> i % 2 == 0)
//                .sorted((x, y) -> y - x)
//                .map(i -> Integer.valueOf(new StringBuilder("" + i).reverse().toString()))
//                .filter(i -> i % 2 != 0)
//                .forEach(System.out::println);

        /* Here we will depict the internal of stream
         *   Scenario -1 :
         *               just try one thing comment the stream terminal operation like foreach
         *               it will print nothing this signifies streams are lazy evaluated
         *  Scenario -2 :
         *               try comment all the methods and except 1st map and last foreach and see the o/p
         *  Scenario -3 :
         *               try to comment all the methods except map,filter anf foreach
         *  Scenario -4 :
         *               try to comment all the methods except map,filter again map and foreach
         *  Scenario -5 :
         *               try to comment all the methods except map,filter again map,filter and foreach
         *
         *   - from above all scenarios we can depict the flow exection
         *
         *  Scenario -6 :
         *              uncomment the sort method bcz sort method cant work on single value
         *               it need results collected to be sorted
         * */

        Stream<Integer> strm2 = Stream.of(nums);
        strm2
                .map(i -> {
                    System.out.println("squaring : " + i);
                    return i * i;
                })
                .filter(i -> {
                    System.out.println("filtering even : " + i);
                    return i % 2 == 0;
                })
                .sorted((x, y) -> {
                    System.out.println("sorting : compare - " + x + ", " + y);
                    return y - x;
                })
                .map(i -> {
                    System.out.println("reversing : " + i);
                    return Integer.valueOf(new StringBuilder("" + i).reverse().toString());
                })
                .filter(i -> {
                    System.out.println("filtering odd : " + i);
                    return i % 2 != 0;
                })
                .forEach(i -> System.out.println("forEach : " + i));
        System.out.println();

        /* Here we talked about the performance of the stream
        * like effective way of writing streams
        * here initially we are mapping then doing sorting and then again doing filtering
        *
        * Instead we should 1st do filtering and then sorting
        * so that we can do the less number of sorting bcz of filtering
        * */
        Stream<String> strm3 = Stream.of("Smita", "Rahul", "Rachana", "Amit", "Shraddha", "Nilesh", "Sandeep", "Pradnya", "Rohan", "Sarika");
        strm3
                .map(s -> s.toUpperCase())
                .sorted((x, y) -> -x.compareTo(y))
                .filter(s -> s.endsWith("A"))
                .forEach(System.out::println);
        System.out.println();

        /*Here we have introduced a new method as Peek which takes Consumer as FI param
        * we used it to just print things
        * apart from that we are just doing what we did above*/
        Stream<String> strm4 = Stream.of("Smita", "Rahul", "Rachana", "Amit", "Shraddha", "Nilesh", "Sandeep", "Pradnya", "Rohan", "Sarika");
        strm4
                .peek(x -> System.out.println("mapping: " + x))
                .map(s -> s.toUpperCase())
                .peek(x -> System.out.println("sorting: " + x))
                .sorted((x,y) -> -x.compareTo(y))
                .peek(x -> System.out.println("filtering: " + x))
                .filter(s -> s.endsWith("A"))
                .peek(x -> System.out.println("printing: " + x))
                .forEach(System.out::println);
    }
}

