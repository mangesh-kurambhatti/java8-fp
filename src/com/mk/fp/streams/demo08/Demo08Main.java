package com.mk.fp.streams.demo08;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class SumTask extends RecursiveTask<Long> {
    private Integer[] arr;
    int low, high, stage;
    public SumTask(Integer[] arr, int low, int high, int stage) {
        this.arr = arr;
        this.low = low;
        this.high = high;
        this.stage = stage;
    }
    @Override
    protected Long compute() {
        if(stage == 1) {
            SumTask[] tasks = new SumTask[4];
            int partLen = arr.length/4;
            for (int i = 0; i < tasks.length; i++) {
                tasks[i] = new SumTask(arr, low+i*partLen, low+i*partLen+partLen, 2);
                tasks[i].fork();
            }
            long result = 0;
            for (int i = 0; i < tasks.length; i++) {
                result += tasks[i].join();
            }
            return result;
        } else {
            long partResult = 0;
            for(int i=low; i<high; i++)
                partResult += arr[i];
            return partResult;
        }
    }
}

public class Demo08Main {
    public static void main(String[] args) {
  //      System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "4");
        ForkJoinPool pool = ForkJoinPool.commonPool();
        System.out.println("Parallelism: " + pool.getParallelism());
        System.out.println();

        /*
        Integer[] arr = Stream.iterate(1, i->i+1).limit(16).toArray(Integer[]::new);
        int res3a = Stream.of(arr)
                .reduce(0, Integer::sum);
        System.out.println("Sequential Sum = " + res3a);

        long res3b = pool.invoke(new SumTask(arr, 0, arr.length-1, 1));
        System.out.println("ForkJoin Sum = " + res3b);

        int res3c = Stream.of(arr)
                .parallel()
                .reduce(0, Integer::sum);
        System.out.println("Parallel Sum = " + res3c);
        System.out.println();

        Stream<Integer> strm1 = Stream.iterate(1, i->i+1).limit(10);
        strm1
                .filter(i -> {
                    System.out.printf("Filtering %d [%s]\n", i, Thread.currentThread().getName());
                    return true;
                })
                .map(i -> {
                    System.out.printf("Squaring %d [%s]\n", i, Thread.currentThread().getName());
                    return i*i;
                })
                .forEach(i -> {
                    System.out.printf("Printing %d [%s]\n", i, Thread.currentThread().getName());
                });
        System.out.println();

        Stream<Integer> strm2 = Stream.iterate(1, i->i+1).limit(10);
        strm2
                .parallel()
                .filter(i -> {
                    System.out.printf("Filtering %d [%s]\n", i, Thread.currentThread().getName());
                    return true;
                })
                .map(i -> {
                    System.out.printf("Squaring %d [%s]\n", i, Thread.currentThread().getName());
                    return i*i;
                })
                .forEach(i -> {
                    System.out.printf("Printing %d [%s]\n", i, Thread.currentThread().getName());
                });
        System.out.println();


        Stream<Integer> strm3 = Stream.iterate(1, i->i+1).limit(10);
        List<Integer> list3 = strm3.collect(Collectors.toList());
        Collections.shuffle(list3);

        list3.parallelStream()
                .filter(i -> {
                    System.out.printf("Filtering %d [%s]\n", i, Thread.currentThread().getName());
                    return true;
                })
                .sorted((x,y) -> {
                    System.out.printf("Sorting %d <> %d [%s]\n", x, y, Thread.currentThread().getName());
                    return x-y;
                })
                .map(i -> {
                    System.out.printf("Squaring %d [%s]\n", i, Thread.currentThread().getName());
                    return i*i;
                })
                .forEach(i -> {
                    System.out.printf("Printing %d [%s]\n", i, Thread.currentThread().getName());
                });
        System.out.println();

         */
    }
}

