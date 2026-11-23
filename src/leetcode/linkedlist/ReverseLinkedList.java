package leetcode.linkedlist;



public class ReverseLinkedList {
    // Definition for singly-linked list node.
    /**
     * Reverses a singly-linked list iteratively.
     * 
     * @param head The head of the linked list
     * @return The new head of the reversed linked list
     */
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        
        while (current != null) {
            ListNode nextTemp = current.next; // Store the next node
            current.next = prev;            // Reverse the current node's pointer
            prev = current;                 // Move prev one step forward
            current = nextTemp;             // Move current one step forward
        }
        
        // 'prev' will be pointing to the new head of the reversed list
        return prev;
    }

    // Helper method to print the linked list
    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        // Creating a linked list: 1 -> 2 -> 3 -> 4 -> 5 -> null
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.print("Original List: ");
        printList(head);

        // Reverse the list
        head = reverseList(head);

        System.out.print("Reversed List: ");
        printList(head);
        // Output: 5 -> 4 -> 3 -> 2 -> 1 -> null
    }
}