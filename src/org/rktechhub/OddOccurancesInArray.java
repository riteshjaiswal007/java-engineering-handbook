package org.rktechhub;

import java.util.HashSet;
import java.util.Set;

public class OddOccurancesInArray {

    public static void main(String[] args){
        int[] a={9,1,9,2,3,4,5,3,4,5,1};
        Set<Integer> set=new HashSet<>();
        for(int i:a){
            if(set.contains(i)){
                set.remove(i);
            }else{
                set.add(i);
            }
        }

        System.out.println("odd" + set);
    }
}
