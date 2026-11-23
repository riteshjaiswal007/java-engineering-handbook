package org.rktechhub;

public class Test1 {

    public static void main(String[] args) {

        int[][][] arr1 = {
                {
                        {1, 1, 1, 1},
                        {2, 2, 2, 2},
                        {3, 3, 3, 3}
                },
                {
                        {1, 1, 1, 1},
                        {2, 2, 2, 2},
                        {3, 3, 3, 3}
                }
        };

        int m = arr1.length;
        int n = arr1[0].length;

        System.out.print("arr1 length is " + m + " row length is " + n);


    }
}
