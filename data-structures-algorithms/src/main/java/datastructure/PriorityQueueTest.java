package datastructure;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueTest {

    public static void main(String[] args) {
        PriorityQueue<Integer> pq1=new PriorityQueue<>();
        //default is minheapify  output will be asc
        //default is maxheapify  output will be desc
        PriorityQueue<Integer> pq2=new PriorityQueue<>(Collections.reverseOrder());

        pq1.offer(3);
        pq1.poll();

        PriorityQueue<Employee> pq3=new PriorityQueue<>(Comparator.comparing(Employee::getName).reversed());
        pq3.offer(new Employee("D"));
        pq3.offer(new Employee("B"));

        pq3.forEach(e->System.out.println("Name"+e.getName()));

    }
}
