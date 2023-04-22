package com.mk.fp.lambda.demo01;


import java.io.Console;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.function.Consumer;

import javax.swing.Timer;

public class Demo01Main {
    public static void lambdaInThread() {
        try {
            Thread t = new Thread(() -> {
                for (int i = 1; i <= 10; i++) {
                    System.out.println("Thread: " + i);
                    try{ Thread.sleep(500); } catch (Exception e) {}
                }
            });
            t.start();
            t.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void repeatCall(int cnt, Consumer<String> c) {
        for (int i = 1; i <= cnt; i++) {
            c.accept("Hello Lambda " + i);
        }
    }

    public static <T> void sort(T arr[], Comparator<T> c) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if(c.compare(arr[i], arr[j]) > 0) {
                    T temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }


    public static void main(String[] args) {

        /*
            Here we have implemented our own sorting method
            Where we have used Comparator for sorting
            And invoked that comparator using lambda
        * */

        //repeatCall(5, (s)->System.out.println(s));

        // lambdaInThread();

//        Timer t = new Timer(1000, (e)->System.out.println("Hello Swing Timer."));
//        t.start();

        System.out.println("Press enter to exit...");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        sc.close();
    }
}
