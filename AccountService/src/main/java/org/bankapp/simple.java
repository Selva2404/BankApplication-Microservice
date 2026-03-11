package org.bankapp;

import java.util.*;
import java.util.function.Function;

import java.util.stream.Collectors;


public class simple {

    public static void main(String args[]) {
        //List<String> list= Arrays.asList("ajith","adjnd","jsdnjs","aubs");
//        List<Integer> list= Arrays.asList(1,3,2,-4,6,-5,-7,8,9,-10);
//        List<Integer> sorted = list.stream()
//                .filter(e -> e%3 == 0 )
//                .toList();
//        System.out.println(sorted);
        String l="selva";
        System.out.println(l);
        String s="selva";
         s.concat("ganapathi");
        System.out.println(s);
        System.out.println(l);

//        List<Employee> list = Arrays.asList(
//                new Employee("selva", "dev", 30000),
//                new Employee("sel", "dev", 150000),
//                new Employee("selv", "it", 90000),
//                new Employee("se", "hr", 50000),
//                new Employee("s", "hr", 70000),
//                new Employee("selva", "it", 60000)
//        );
//
//        List<Employee> list1 = list.stream()
//                .filter(e -> e.salary() > 10000)
//                .collect(Collectors.groupingBy(Employee::name))
//                .values().stream()
//                .filter(v -> v.size() > 1)
//                .flatMap(List::stream)
//                .toList();

        String w="Selvagantapathi";
        List<Map.Entry<Character, Long>> list = w.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > 1)
                .skip(1)
                .toList();

        System.out.println(list);
    }
}



