package leetcode.twopointers;

public class StringPalindrome {

    public static void main(String[] args){
        String s="asdfdsaj";

        int start=0;
        int end=s.length()-1;
        while(start<end){

            if(s.charAt(start)!=s.charAt(end)){
                System.out.println("false");
                break;
            }

            start++;
            end--;

        }
        System.out.println("true");
    }
}
