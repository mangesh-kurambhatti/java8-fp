package com.mk.fp.streams.demo06;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.Map.Entry;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;


class Emp {
    private int empno;
    private String ename;
    private double sal;
    private String job;
    public Emp() {
        this(0, "", 0.0, "");
    }
    public Emp(int empno, String ename, double sal, String job) {
        this.empno = empno;
        this.ename = ename;
        this.sal = sal;
        this.job = job;
    }
    public int getEmpno() {
        return empno;
    }
    public void setEmpno(int empno) {
        this.empno = empno;
    }
    public String getEname() {
        return ename;
    }
    public void setEname(String ename) {
        this.ename = ename;
    }
    public double getSal() {
        return sal;
    }
    public void setSal(double sal) {
        this.sal = sal;
    }
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }
    @Override
    public String toString() {
        return "Emp [empno=" + empno + ", ename=" + ename + ", sal=" + sal + ", job=" + job + "]";
    }

    public static int compareJobDesc(Emp e1, Emp e2) {
        return e2.getJob().compareTo(e1.getJob());
    }
}

public class Demo06Main {
    public static void main(String[] args) {
        List<Emp> list = Arrays.asList(
                new Emp(5, "S", 745, "Clerk"),
                new Emp(9, "D", 834, "Manager"),
                new Emp(7, "K", 345, "Salesman"),
                new Emp(2, "G", 534, "Analyst"),
                new Emp(4, "W", 742, "Manager"),
                new Emp(1, "U", 875, "Manager"),
                new Emp(8, "B", 528, "Salesman"),
                new Emp(3, "N", 656, "Clerk")
        );

        /*Scenario-0
        * Joining is used to join strings with delimiter,suffix and prefix
        *
        * */
        Stream<Emp> strm0 = list.stream();
        String res0 = strm0
                .filter(e -> e.getSal() > 700)
                .map(Emp::getEname)
                .collect(Collectors.joining(", ", "In company ", " are rich."));
        System.out.println(res0);
        System.out.println();

        /*Scenario-1
        Here Double::MAX reperesent that whereever keys get duplicated choose the max thats why its a merger param
        This is like finding dept wise max salary
        Salesman -> 528.0
        Analyst -> 534.0
        Clerk -> 745.0
        Manager -> 875.0
        * */
        Stream<Emp> strm1 = list.stream();
        Map<String, Double> res1 = strm1.collect(Collectors.toMap(Emp::getJob, Emp::getSal, Double::max));
        res1.forEach((k,v) -> System.out.println(k + " -> " + v));
        System.out.println();
/*
        Stream<Emp> strm2 = list.stream();
        Map<Integer, Integer> res2 = strm2
                .map(e -> 100 * (int)(e.getSal() / 100))
                .collect(Collectors.toMap(Integer::intValue, s->1, Integer::sum));
        res2.forEach((k,v) -> System.out.println(k + " -> " + v));
        System.out.println();
*/
        /*Here we are doing the word count example
        * where we will print the 1st 5 most  */
        try(Stream<String> strm4 = Files.lines(Paths.get("src/sunbeam.txt"))) {
            strm4
                    .map(line->line.toLowerCase())
                    .flatMap(line->Stream.of(line.split("\\s+")))
                    .collect(Collectors.toMap(word->word, word->1, Integer::sum))
                    .entrySet().stream()
                    .sorted(Comparator.comparing(Entry<String,Integer>::getValue).reversed())
                    .limit(5)
                    .forEach((kv)->System.out.println(kv));
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}