package org.rktechhub;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MinPositiveInteger {

    public static void main(String[] args){
        Set<Integer> set= new HashSet<>();
        int[] m={2,3,5,6,7};
        for(int i:m){
            set.add(i);
            System.out.println();
        }
        int smallest=1;
        while(smallest<100000 && set.contains(smallest)){
            smallest++;
        }
       System.out.println("small"+smallest);
    }
}
