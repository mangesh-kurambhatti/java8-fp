package com.mk.fp.lambda.demo03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
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

public class Demo03Main {
    public static void printEmps(List<Emp> l) {
        for (Emp e : l)
            System.out.println(e);
        System.out.println();
    }
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

        // sort by job asc
        Collections.sort(list, (e1,e2)->e1.getJob().compareTo(e2.getJob()));
        printEmps(list);
        System.out.println();

        // sort by job desc
        Collections.sort(list, Emp::compareJobDesc);
        list.forEach(System.out::println);
        System.out.println();

        // sort by job asc
        Collections.sort(list, Comparator.comparing(Emp::getJob));
        list.forEach(System.out::println);
        System.out.println();

        // sort by job desc and then by name desc
        list.sort((e1,e2)-> {
            int diff = e2.getJob().compareTo(e1.getJob());
            if(diff == 0)
                diff = e2.getEname().compareTo(e1.getEname());
            return diff;
        });
        list.forEach(System.out::println);
        System.out.println();

        // sort by job asc and then by name asc
        list.sort(Comparator.comparing(Emp::getJob).thenComparing(Emp::getEname));
        list.forEach(System.out::println);
        System.out.println();

        // sort strings ignoring case
        List<String> strList = Arrays.asList("sarang", "Nilesh", "Amit", "Nitin", "Vishal", "Sarang", "nitin");
        strList.sort(String::compareToIgnoreCase);
        System.out.println(strList);
        System.out.println();
    }
}

