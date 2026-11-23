package org.rktechhub;

import java.util.Scanner;

public class ScannerTest2 {
    /**
     *
     * The first line contains an integer.
     * The second line contains a double.
     * The third line contains a String.
     * @param args
     */
    public static void main(String[] args){
        Scanner scan=new Scanner(System.in);

        int n1=scan.nextInt();
        double d1= scan.nextDouble();
        String s1= scan.next();


        scan.close();

        System.out.println(n1);
        System.out.println(d1);
        System.out.println(s1);

    }
}
