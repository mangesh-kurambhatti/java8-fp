package com.mk.fp.streams.demo07;



import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class DailyStatus {
    private LocalDate date;
    private String state;
    private String category;
    private int count;
    public DailyStatus() {
    }
    public DailyStatus(LocalDate date, String state, String category, int count) {
        this.date = date;
        this.state = state;
        this.category = category;
        this.count = count;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public String getMonth() {
        return this.date.getMonth().toString();
    }
    @Override
    public String toString() {
        return "DailyStatus [date=" + date + ", state=" + state + ", category=" + category + ", count=" + count + "]";
    }
}

public class Demo07Main {
    final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yy");
    final static String[] header = "Date,Status,TT,AN,AP,AR,AS,BR,CH,CT,DN,DD,DL,GA,GJ,HR,HP,JK,JH,KA,KL,LA,LD,MP,MH,MN,ML,MZ,NL,OR,PY,PB,RJ,SK,TN,TG,TR,UP,UT,WB".split(",");
    public static Stream<DailyStatus> parseLine(String line) {
        List<DailyStatus> list = new ArrayList<DailyStatus>();
        String[] parts = line.split(",");
        try {
            LocalDate date = formatter.parse(parts[0], LocalDate::from);
            String category = parts[1];
            for (int i = 2; i < parts.length; i++) {
                String state = header[i];
                try {
                    int count = Integer.parseInt(parts[i]);
                    list.add(new DailyStatus(date, state, category, count));
                }catch (Exception e) {
                    continue;
                }
            }
        } catch (Exception e) {

        }
        return list.stream();
    }

    public static void main(String[] args) {
        try(Stream<String> strm1 = Files.lines(Paths.get("src/state_wise_daily.csv"))) {
            strm1
                    .flatMap(Demo07Main::parseLine)
                    .filter(s -> !s.getState().equals("TT"))
                    .filter(s -> s.getCategory().equals("Confirmed"))
                    .collect(Collectors.toMap(DailyStatus::getState, DailyStatus::getCount, Integer::sum))
                    .entrySet().stream()
                    .sorted(Comparator.comparing(Entry<String,Integer>::getValue).reversed())
                    .limit(10)
                    .forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();

        /*Guess what will be the output*/
        try(Stream<String> strm2 = Files.lines(Paths.get("src/state_wise_daily.csv"))) {
            strm2
                    .flatMap(Demo07Main::parseLine)
                    .filter(s -> !s.getState().equals("TT"))
                    .collect(Collectors.toMap(DailyStatus::getCategory, DailyStatus::getCount, Integer::sum))
                    .entrySet().stream()
                    .forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();

        /*Scenario-3 refers the same file go through the code and understand the scenario*/
        try(Stream<String> strm3 = Files.lines(Paths.get("src/state_wise_daily.csv"))) {
            strm3
                    .flatMap(Demo07Main::parseLine)
                    .filter(s -> s.getState().equals("TT"))
                    .collect(Collectors.toMap((DailyStatus d)->d.getMonth() + " " + d.getCategory(), DailyStatus::getCount, Integer::sum))
                    .entrySet().stream()
                    .sorted(Entry.comparingByKey())
                    .forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

