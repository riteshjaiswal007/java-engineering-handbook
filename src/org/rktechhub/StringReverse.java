package org.rktechhub;

public class StringReverse {

    public static void main(String[] args){


        //R i t e s h  asdfdsa

        //012345
        String str="Ritesh";
        char[] ch=str.toCharArray();

        int start=0;
        int end=str.length()-1;

        while(start<end){
            char c=ch[start];
            ch[start]=ch[end];
            ch[end]=c;

            start++;
            end--;
        }
        String reverse=new String(ch);
        System.out.println(reverse);
    }
}
