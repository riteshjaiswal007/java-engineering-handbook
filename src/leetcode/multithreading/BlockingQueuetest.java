package leetcode.multithreading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueuetest {
    public static void main(String[] args) {

        BlockingQueue<Integer> queue=new ArrayBlockingQueue<>(1);

        Thread p1=new Thread(()->{
            while(true){
                try {
                    queue.put(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("p thread adding " + 1);
            }
        });
        Thread c1=new Thread(()->{
            while(true){
                try {
                   int n= queue.take();
                    System.out.println("C thread removing " + n);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        p1.start();
        c1.start();
    }
}
