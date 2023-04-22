package com.mk.fp.intf.demo08;


import java.util.Arrays;
import java.util.Comparator;

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
}

public class Demo08Main {

    public static void printEmp(Emp[] obj){
        for (Emp e:obj ) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {

        Emp[] arr = new Emp[] {
                new Emp(5, "S", 745, "Clerk"),
                new Emp(9, "D", 834, "Manager"),
                new Emp(7, "K", 345, "Salesman"),
                new Emp(2, "G", 534, "Analyst"),
                new Emp(4, "W", 742, "Manager"),
                new Emp(1, "U", 875, "Manager"),
                new Emp(8, "B", 528, "Salesman"),
                new Emp(3, "N", 656, "Clerk")
        };

        printEmp(arr);

        /*
        * We are trying below thing with comparator bcz its an FP
        * it contains only 1 method compare()
        * We will se how code size get reduces as we see different scenarios
        * */

        //Scenario- 1
        //Here we have created a class which implements comparator Interface
        //and executed the sort method
        class EmpComparatorAsc implements Comparator<Emp>{

            @Override
            public int compare(Emp o1, Emp o2) {
                return o1.getEmpno() - o2.getEmpno();
            }
        }
        Arrays.sort(arr,new EmpComparatorAsc());
        System.out.println();
        printEmp(arr);


        //Scenario-2
        //Here we have created annonymos inner class with name $1
        //we can see the .class file at bin
        //here we have sorted the arr using name
        //This is another way of doing this
        Comparator<Emp> empComparatorAsc= new Comparator<Emp>() {
            @Override
            public int compare(Emp o1, Emp o2) {
                return o1.getEname().compareTo(o2.getEname());
            }
        };

        Arrays.sort(arr,empComparatorAsc);
        System.out.println();
        printEmp(arr);


        //Scenario-3
        /*
        * Sorting the array DESC
        * here we created or implemented method directly in sort method itself
        *
        * here class is anonymous and object is also anonymous
        * we can check this by looking in bin
        * we will have $2 .class file
        * */

        Arrays.sort(arr, new Comparator<Emp>() {
            @Override
            public int compare(Emp o1, Emp o2) {
                return o2.getEname().compareTo(o1.getEname());
            }
        });
        System.out.println();
        printEmp(arr);

        //Scenario-4

        /*
        *for explanantion look into book
        * */

        Arrays.sort(arr,(Emp o1,Emp o2)->{
            return o1.getEmpno()-o2.getEmpno();
        });
        System.out.println();
        printEmp(arr);

        //Scenario-5

        /*
         * Here we have removed curly brackets from body
         * and if we do so we need to remove return statement as well from there
         *
         * it needs expression only, we need to remove statements
         * */

        Arrays.sort(arr,(Emp o1,Emp o2)-> o1.getEmpno()-o2.getEmpno());
        System.out.println();
        printEmp(arr);

        //Scenario-6

        /*
        * Here, as compiler knows this is going to sort emp array from 1st param
        * we dont need to give object type as well
        * */
        Arrays.sort(arr,(o1,o2)-> o1.getEmpno()-o2.getEmpno());
        System.out.println();
        printEmp(arr);
    }




}
