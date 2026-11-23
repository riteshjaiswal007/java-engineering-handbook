package org.rktechhub;

public class ArrayProduct {
    public static void main(String[] args) {
        int[] arr={1,2,3,4};

        int[] output=new int[arr.length];

        //add prefix
        output[0]=1;
        for (int i = 1; i < arr.length; i++) {
          output[i]=output[i-1]*arr[i-1];
        }

       // System.out.print("prefix arr"+output.toString());
        //addpostfix
        int postfix=1;
        for (int i = arr.length-1; i >=0; i--) {
              output[i]=postfix*output[i];
              postfix=postfix*arr[i];
        }
        for (int i: output ) {
            System.out.print(" "+i);
        }

    }
}
