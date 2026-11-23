package Streamtest;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapTest {

    public static void main(String[] args) {
        List<String> list=List.of("CC","DD","DD","A","B","A","B");


        //create Map
       Map<String, Long> map= list.stream().collect(Collectors.toMap(s->s, s->1L,Long::sum));

        printMap(map);

        //sort map
        Map<String, Long> map2=  map.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(e->e.getKey(),e->e.getValue(),(a,b)->a, LinkedHashMap::new));
        printMap(map2);

        //groupingby
        Map<Object, List<String>> map3=  list.stream().collect(Collectors.groupingBy(s -> s.charAt(0)));
        System.out.println(map3);

        Map<String, Long> map4=    list.stream().sorted().collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()));
        System.out.println(map4);
    }

    private static void printMap(Map<String, Long> map) {
        map.forEach((k, v)->System.out.print(" "+k+" "+v+","));
        System.out.println("");
    }
}
