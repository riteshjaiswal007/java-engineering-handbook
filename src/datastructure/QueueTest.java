package datastructure;

import java.util.*;

public class QueueTest {
    public static void main(String[] args){
        // Instantiate using LinkedList
        Deque<String> line = new ArrayDeque<>();

        // 1. Enqueue (Insert Elements)
        line.offer("Alice");
        line.offer("Bob");
        line.offer("Charlie");
        System.out.println("Initial Queue: " + line); // Output: [Alice, Bob, Charlie]

        // 2. Peek (Examine Head Element)
        String frontPerson = line.peek();
        System.out.println("Front Element (Peek): " + frontPerson); // Output: Alice

        // 3. Dequeue (Remove Elements)
        String removedPerson = line.poll();
        System.out.println("Removed Element (Poll): " + removedPerson); // Output: Alice
        System.out.println("Queue after removal: " + line); // Output: [Bob, Charlie]

        // 4. Utility Operations
        System.out.println("Is queue empty? " + line.isEmpty()); // Output: false
        System.out.println("Queue size: " + line.size()); // Output: 2
    }
}
