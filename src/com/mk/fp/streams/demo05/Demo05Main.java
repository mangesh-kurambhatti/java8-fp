package com.mk.fp.streams.demo05;


import java.util.AbstractMap.SimpleEntry;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo05Main {
    public static void main(String[] args) {
        Integer[] nums = {23, 32, 67, 89, 45, 76, 12, 34, 56, 12, 78, 32, 54};

        /*Scenario -1
        * Here we have added 1 to each element using map operation
        * Ques is what we achieved using Collectors.toSet()
        * Are able to remove duplicates from new stream
        * Homework do same for toList toArray etc. all operations
        * */
        Stream<Integer> strm1 = Stream.of(nums);
        Set<Integer> res1 = strm1
                .map(i->i+1)
                .collect(Collectors.toSet());
        res1.forEach(System.out::println);
        System.out.println();

        /*Scenario-2
        * internally its converting Integers stream to int (primitve type) //unboxing
        * This gives us o/p as int operations
        * */
        Stream<Integer> strm2 = Stream.of(nums);
        IntSummaryStatistics res2 = strm2
                .collect(Collectors.summarizingInt(i -> i));
        System.out.println(res2);
        System.out.println();

        /*Scenario-3
        * SimpleEntry its satic class inside Collection
        * Interger is getting into SimpleENtry which takes two things
        * We are taking every any number into pair of string and number
        *   Example-:
        *       12 -> <"Even",12
        *       13 -> <"Odd",13
        *  Like this internal DS looks like
        *
        *   As we are collecting data into toArray we can't use foreach terminal operation
        * */
        Stream<Integer> strm3 = Stream.of(nums);
        Object[] res3 = strm3
                .map(n -> new SimpleEntry<String, Integer>(n%2==0 ? "Even" : "Odd", n))
                .toArray();
        for (Object obj : res3)
            System.out.println(obj);
        System.out.println();

        /*Scenario-4
        * Collecting to List is the only change happened here
        * As we use List we got the freedom to use foreach*/

        Stream<Integer> strm4 = Stream.of(nums);
        List<SimpleEntry<String, Integer>> res4 = strm4
                .map(n -> new SimpleEntry<String, Integer>(n%2==0 ? "Even" : "Odd", n))
                .collect(Collectors.toList());
        res4.forEach(System.out::println);
        System.out.println();

        /*Scenario-5
        * Question - We want to filter data even and odd
        * Note -: TODO - Partioning in streams can be in only 2 parts only
        *          Also partitioning is always onpredicate so its giving boolean value
        * if we run 1st part we will see something o/p like
        *       Key :false value :[23, 67, 89, 45]
                Key :true value :[32, 76, 12, 34, 56, 12, 78, 32, 54]
        * Now the key should get converted to labels as odd and even then we will print the length of numbers or count of numbers
        * in front of them.
        * */

        Stream<Integer> strm5 = Stream.of(nums);
        Map<Boolean, List<Integer>> res5 = strm5
                .collect(Collectors.partitioningBy(i -> i%2 == 0));
        res5.forEach((k,v)-> System.out.println("Key :"+k+" value :"+v));
        res5.entrySet()
                .stream()
                .map(e -> new SimpleEntry<>(e.getKey()?"Even":"Odd", e.getValue().size()))
                .forEach(System.out::println);
        System.out.println();

        /*Scenario-6
        * like above here we have added 'Collectors.counting()'
        * which directly gives us count
        * and using map we just change the labels of key's
        * */
        Stream<Integer> strm6 = Stream.of(nums);
        Map<Boolean, Long> res6 = strm6
                .collect(Collectors.partitioningBy(i -> i%2 == 0, Collectors.counting()));
        res6.entrySet()
                .stream()
                .map(e -> new SimpleEntry<>(e.getKey()?"Even":"Odd", e.getValue()))
                .forEach(System.out::println);
        System.out.println();

        /*Scenario-7
        *  TODO : groupngBy is depend on number of unique keys we have*/
        Stream<Integer> strm7 = Stream.of(nums);
        Map<String, List<SimpleEntry<String, Integer>>> res7 = strm7
                .map(n -> new SimpleEntry<>(n%2==0 ? "Even" : "Odd", n))
                .collect(Collectors.groupingBy(SimpleEntry::getKey));
        res7.entrySet()
                .stream()
                .map(e -> new SimpleEntry<>(e.getKey(), e.getValue().size()))
                .forEach(System.out::println);
        System.out.println();

        Stream<Integer> strm8 = Stream.of(nums);
        Map<Object, Long> res8 = strm8
                .map(n -> new SimpleEntry<>(n%2==0 ? "Even" : "Odd", n))
                .collect(Collectors.groupingBy(SimpleEntry::getKey, Collectors.counting()));
        System.out.println(res8);

        /*Here neither we have used groupBy or partitions
        *
        * 1 -> k=odd
        * 2 -> k=even
        * 3 -> k=odd
        * 4 -> k=even
        * 5 -> k=odd
        *
        * Map will looks like
        *   see book for explanation and diagram
        * */
        Stream<Integer> strm9 = Stream.of(nums);
        Map<String, Integer> res9 = strm9
                .collect(Collectors.toMap(k->k%2==0?"Even":"Odd", v->1, Integer::sum));
        System.out.println(res9);
        System.out.println();

        /*How to find max odd and even number*/
        Stream<Integer> strm10 = Stream.of(nums);
        Map<String, Integer> res10 = strm10
                .collect(Collectors.toMap(k->k%2==0?"Even":"Odd", v->v, Integer::max));
        System.out.println(res10);
        System.out.println();
    }
}