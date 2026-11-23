package org.rktechhub;

public class Print2dArray {

    public static void main(String[] args) {
        //2d array 2*3
        int[][] arr1 = {
                {1, 2, 3},
                {4, 5, 6}
        };
        //1st row
        System.out.print(arr1[0][0]);
        System.out.print(arr1[0][1]);
        System.out.print(arr1[0][2]);

        System.out.print("\n");
        //2nd row
        System.out.print(arr1[1][0]);
        System.out.print(arr1[1][1]);
        System.out.print(arr1[1][2]);

        int[][] arr2 = new int[2][3];
        int value = 10;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                arr2[i][j] = value;
                value++;
            }
        }
        System.out.print("\n");
        //1st row
        System.out.print(arr2[0][0] + " ");
        System.out.print(arr2[0][1] + " ");
        System.out.print(arr2[0][2] + " ");

        System.out.print("\n");
        //2nd row
        System.out.print(arr2[1][0] + " ");
        System.out.print(arr2[1][1] + " ");
        System.out.print(arr2[1][2]);
        System.out.print("\n");
        //print 2d array using loop
        for (int[] m1:arr2) {
            for (int value2:m1) {
                System.out.print(value2 + " ");
            }
            System.out.print("\n");
        }

    }
}
