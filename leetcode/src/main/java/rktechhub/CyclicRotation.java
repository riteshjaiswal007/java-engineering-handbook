package rktechhub;

public class CyclicRotation {

    public static void main(String[] args){

        int[] A = {3, 8, 9, 7, 6};

        int k=3;

      while(k>0){
          int pre=A[0];
          int next=A[0];
        for (int i = 1; i < A.length; i++) {
            next=A[i];
            A[i]=pre;
           pre=next;
        }
        A[0]=next;
        k--;
    }
        for (int b: A
             ) {
            System.out.println("ll"+b);
        }

}}
