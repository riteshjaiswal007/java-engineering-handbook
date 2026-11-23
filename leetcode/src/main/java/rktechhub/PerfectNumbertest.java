package rktechhub;

public class PerfectNumbertest {


    // N=6  1,2,3     6
    //1+2+3=6 //perfect number
    //28 1,2,4,7,14 ,  28/2   , 14 ,

    // 1  2* 14,

    public static boolean isPerfectNumber(long n){
        if (n < 2) return false;

        long sum = 1; // 1 is always a proper divisor for any n > 1

        // Find divisors up to the square root of n
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                sum += i;
                long otherDivisor = n / i;
                if (otherDivisor != i) {
                    sum += otherDivisor; // Add the paired divisor
                }
            }
        }

        return sum == n;
    }

     public static void main(String[] args){
         int targetCount = 5;
         int foundCount = 0;
         long number = 2; // Start checking from 2

         System.out.println("Finding the first 5 perfect numbers...");

         while (foundCount < targetCount) {
             if (isPerfectNumber(number)) {
                 System.out.println("Perfect Number found: " + number);
                 foundCount++;
             }
             number++;
         }

   }
}
