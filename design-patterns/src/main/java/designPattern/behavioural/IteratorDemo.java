package designPattern.behavioural;

import java.util.*;
/*11. Iterator Pattern
        Purpose: Provides a standard way to traverse a collection
        without exposing its internal representation.
        Real-world example: Iterating through a list of employees.*/
public class IteratorDemo {
    public static void main(String[] args) {
        List<String> employees =
            List.of("John", "David", "Alex");

        Iterator<String> iterator = employees.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}