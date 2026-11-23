package Streamtest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Listtest {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 2, 4, 3, 5,2);

        Set<Integer> seen = new HashSet<>();


        List<Integer>unique=list.stream().filter(num->seen.add(num)).collect(Collectors.toList());
        System.out.println(unique);
        seen.clear();
        List<Integer> duplicates = list.stream()
                .filter(n -> !seen.add(n)).distinct()
                .collect(Collectors.toList());

        System.out.println(duplicates);


    }
}
