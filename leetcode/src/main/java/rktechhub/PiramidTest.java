package rktechhub;

public class PiramidTest {
//1,,3,5,7,9,11,13,15,17,19
    //10*2 -1
    //10-1


    public static void main(String[] args){


        for(int n=1;n<=10;n++){

            for(int m=10-n;m>0;m--){
                System.out.print(" ");
            }

            for(int j=1;j<=(2*n-1);j++) {
                System.out.print("*");
            }

            System.out.println();
        }

    }
}
