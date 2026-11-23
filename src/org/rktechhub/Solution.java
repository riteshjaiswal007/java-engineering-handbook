package org.rktechhub;

import java.util.*;

public class Solution {

    public static void main(String[] args){

        String s="abcdefgh";
        char ch ='c';

        for (int i = 0; i < s.length(); i++) {
            System.out.print(s.charAt(i));

        }

        char[] chr=s.toCharArray();

        for (int i = 0; i < chr.length; i++) {
            System.out.println( "char"+ chr[i]);
        }

        for (int i = 0; i < chr.length; i++) {
            System.out.println("charToInt"+(int)chr[i]);
        }
        int[] a={1,2,3,4,5,6,7,8,9};
        List<Integer> list=new ArrayList<>();

        for (int i = 0; i < a.length; i++) {
            list.add(a[i]);
        }
        System.out.println("list"+list);
        for (Integer m:list  ) {
            System.out.println("list"+m);
        }
        for (int i = 0; i < a.length; i++ ) {
            System.out.println("list"+list.get(i));
        }
        Set<Integer> set=new HashSet<>();

                set.add(3);
                set.contains(3);

                Map<Integer,String> map=new HashMap<>();
                map.put(2,"Ritesh");
                map.put(3,"Manu");

                for(Integer m:map.keySet()){
                    System.out.println(map.get(m));
                }
                for(Map.Entry<Integer,String> entry:map.entrySet()){
                    System.out.println("Map"+entry.getKey()+"value"+entry.getValue());
                }



    }
}
