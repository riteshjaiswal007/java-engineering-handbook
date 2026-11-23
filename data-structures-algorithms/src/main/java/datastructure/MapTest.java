package datastructure;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapTest {
    public static void main(String[] args) {
        Map<Integer,String> map= new HashMap<>();

        map.put(1,"Ritesh");
        map.getOrDefault(4,"ritesh");
        map.putIfAbsent(3,"Ritesh");
        map.forEach((e,v)-> System.out.println("key "+e+ "Value "+v));
        Map<Integer,String> tmap= new TreeMap<>(Comparator.reverseOrder());

        tmap.put(2,"FF");
        tmap.put(1,"rr");
        tmap.forEach((e,v)-> System.out.println("key "+e+ "Value "+v));


    }
}
