package com.mk.fp.streams.demo03;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

class Transaction {
    private int accId;
    private String type; // Deposit/Withdraw
    private double amount;
    public Transaction() {
        this(0, "", 0.0);
    }
    public Transaction(int accId, String type, double amount) {
        this.accId = accId;
        this.type = type;
        this.amount = amount;
    }
    public int getAccId() {
        return accId;
    }
    public void setAccId(int accId) {
        this.accId = accId;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    @Override
    public String toString() {
        return "Transaction [accId=" + accId + ", type=" + type + ", amount=" + amount + "]";
    }
}

class Account {
    private int accNo;
    private String accType; // Savings/Current
    private List<Transaction> tranList = new ArrayList<>();
    public Account() {
        this(0, "Savings");
    }
    public Account(int accNo, String accType) {
        this.accNo = accNo;
        this.accType = accType;
    }
    public int getAccNo() {
        return accNo;
    }
    public void setAccNo(int accNo) {
        this.accNo = accNo;
    }
    public String getAccType() {
        return accType;
    }
    public void setAccType(String accType) {
        this.accType = accType;
    }
    public List<Transaction> getTranList() {
        return tranList;
    }
    public void setTranList(List<Transaction> tranList) {
        this.tranList = tranList;
    }
    @Override
    public String toString() {
        return "Account [accNo=" + accNo + ", accType=" + accType + ", tranList=" + tranList + "]";
    }
}

public class Demo03Main {
    public static Stream<Integer> factors(int n) {
        Builder<Integer> factorsStrm = Stream.builder();
        for(int i=2; i<=n; i++) {
            if(n % i == 0) {
                factorsStrm.add(i);
                n = n / i;
                i--;
            }
        }
        return factorsStrm.build();
    }

    public static Stream<Integer> factors1(int n) {
        List<Integer> list= new ArrayList<>();
        for(int i=2; i<=n; i++) {
            if(n % i == 0) {
                list.add(i);
                n = n / i;
                i--;
            }
        }
        return list.stream() ;
    }

    public static void main(String[] args) {
        /* Scenario-1
         * Here we hvae used the factorial method which return stream<Integer>
         * There we have used builder to create the stream*/
        Stream<Integer> strm1 = Stream.of(30, 49, 105);
        strm1
                .flatMap(Demo03Main::factors)
                .forEach(System.out::println);
        System.out.println();

        /* Scenario-2
         * Here we have created a stream with using builder method
         * here we have created using Collection
         * */
        Stream<Integer> strm2 = Stream.of(30, 49, 105);
        strm2
                .flatMap(Demo03Main::factors1)
                .forEach(System.out::println);
        System.out.println();

        /*Scenario-3
        * We have see here how to flat the map*/
        Map<String, String[]> friends = new LinkedHashMap<>();
        friends.put("Nilesh", new String[] {"A", "B", "C", "D"});
        friends.put("Amit", new String[] {"P", "Q", "R"});
        friends.put("Nitin", new String[] {"X", "Y"});
        friends.put("Atul", new String[] {"A", "B", "P", "Q", "X", "Y"});

        friends.entrySet().stream()
                .flatMap(p -> Stream.of(p.getValue()))
                //now if we want unique friend only we need to use streams distinct().
                .distinct()
                .forEach(System.out::println);

        /*Scenario-4
        * More complex example where Account class
        * and each account is having multiple transactions
        *   A1 -> T1 -> T2
        *   A2 -> T1 -> T2 -> T3
        *   A3 -> T1
        *
        * Q.1 -> Want to know all transactions in a day
        * Q.2 -> Want to know how many money transacted in a day
        * */

        List<Account> accList = new ArrayList<>();

        Account acc1 = new Account(1, "Savings");
        acc1.getTranList().add(new Transaction(1, "Deposit", 1000.00));
        acc1.getTranList().add(new Transaction(1, "Withdraw", 500.00));
        accList.add(acc1);

        Account acc2 = new Account(2, "Savings");
        acc2.getTranList().add(new Transaction(2, "Deposit", 800.00));
        acc2.getTranList().add(new Transaction(2, "Withdraw", 500.00));
        acc2.getTranList().add(new Transaction(2, "Deposit", 700.00));
        accList.add(acc2);

        Account acc3 = new Account(3, "Savings");
        acc3.getTranList().add(new Transaction(3, "Deposit", 400.00));
        accList.add(acc3);

        accList.stream()
                //Ans of Q1
                .flatMap(acc -> acc.getTranList().stream())
                .forEach(amt -> System.out.println(amt));

        //Ans of Q2
        Double amt= accList.stream()
                .flatMap(acc -> acc.getTranList().stream())
                .mapToDouble(tr -> (tr.getType().startsWith("D") ? +tr.getAmount() : -tr.getAmount() ))
                .reduce(0,Double::sum);

        System.out.println("Amount Transaction done in day is ::"+amt);


    }
}
