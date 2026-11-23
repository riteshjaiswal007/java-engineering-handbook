package leetcode.multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class OddEvenThread {
    public static void main(String[] args) {
        AtomicInteger value=new AtomicInteger(1);
        Thread odd=new Thread(()->{
            synchronized (value){
                while(value.get()<20){
                    if(value.get()%2!=0){
                        System.out.println( "odd number "+ value.getAndIncrement());
                       value.notify();
                    }else{
                        try {
                            value.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });
        Thread even=new Thread(()->{
            synchronized (value){
                while(value.get()<=20){
                    if(value.get()%2==0){
                        System.out.println( " even number "+ value.getAndIncrement());
                        value.notify();
                    }else{
                        try {
                            value.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });

        even.start();
        odd.start();
    }
}
