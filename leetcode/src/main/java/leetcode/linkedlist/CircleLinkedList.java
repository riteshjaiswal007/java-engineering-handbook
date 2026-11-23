package leetcode.linkedlist;

public class CircleLinkedList {
    public static boolean hasCycle(ListNode head){
        // 1  2  3  1
        if(head==null || head.next==null){
            return false;
        }
        ListNode slowPointer=head;
        ListNode fastPointer=head;
        while(fastPointer!=null && fastPointer.next!=null){
            slowPointer =slowPointer.next;
            fastPointer=fastPointer.next.next;
            if(slowPointer==fastPointer){
                return true;
            }

        }
return false;

    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);

        // Creating a cycle: node 4 points back to node 2
        head1.next.next.next.next = head1.next;

        System.out.println("Test 1 - Has cycle: " + hasCycle(head1)); // Output: true

        // --- Test Case 2: Non-Cyclic Linked List ---
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);
        // No cycle created (head2.next.next.next.next remains null)

        System.out.println("Test 2 - Has cycle: " + hasCycle(head2)); // Output: false
    }

}
