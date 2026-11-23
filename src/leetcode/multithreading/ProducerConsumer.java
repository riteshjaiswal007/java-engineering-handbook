package leetcode.multithreading;

import java.util.ArrayList;
import java.util.List;

class PThread implements Runnable {
    private volatile List<Integer> list;
    int capacity;

    PThread(List<Integer> list, int capacity) {
        this.list = list;
        this.capacity = capacity;
    }

    @Override
    public void run() {

            int value = 1;
            while (true) {
                synchronized (list) {
                if (list.size() == capacity) {
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                list.add(value);
                System.out.println("producer adding element " + value);
                list.notify();
                value++;

            }
        }
    }
}

class CThread implements Runnable {
    private volatile List<Integer> list;

    CThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {

            while (true) {
                synchronized (list) {
                if (list.size() == 0) {

                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("Consumer consuming element " + list.remove(0));
                list.notify();
            }
        }
    }
}

public class ProducerConsumer {

    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();
        int capacity = 1;
        Thread p = new Thread(new PThread(l, capacity));
        Thread c = new Thread(new CThread(l));

        p.start();
        c.start();


    }


}
