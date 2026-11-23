package org.rktechhub;

import java.util.Scanner;

public class ScannerTest {

    public static void main(String[] args){

        Scanner scan=new Scanner(System.in);
        System.out.println("Sample Input");
        int n1=scan.nextInt();
        int n2=scan.nextInt();
        int n3=scan.nextInt();
        scan.close();
        System.out.println("Sample output");
        System.out.println(n1);
        System.out.println(n2);
        System.out.println(n3);

    }
}
