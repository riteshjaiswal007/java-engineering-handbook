package org.rktechhub;

public class SecondLargestNumber {

    public static void main(String[] args){

        int[] m={1,2,1,3,6,7,8,4,5,9,9};

        int max=m[0];
        int secondmax=max;

        for (int i = 1; i <m.length ; i++) {
            if(m[i] > max){
                secondmax=max;
                max=m[i];
            } else if (m[i]>secondmax && m[i]<max) {
                secondmax=m[i];
            }
        }

        System.out.println("second "+secondmax);
    }
}
