package rktechhub;

public class LongestBinaryZero {
    public static void main(String[] args){
        int m=121;

        String s=Integer.toBinaryString(m);

        int maxSeq=0;
        System.out.println("s "+s);
        int currentSeq=0;
        for (char c:s.toCharArray()) {
            if(c=='0'){
                currentSeq++;
             //   System.out.println("currentSeq"+currentSeq);
            }else{
                maxSeq=Math.max(maxSeq,currentSeq);
                currentSeq=0;
            }
        }
        System.out.println("maxSeq"+maxSeq);
    }
}
