package leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    class Node{
        int key;
        int value;
        Node next;
        Node prev;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            next=null;
            prev=null;
        }
    }
    Node head;
    Node tail;
    int capacity;

    Map<Integer,Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head=new Node(0,0);
        tail=new Node(0,0);
        head.next=tail;
        tail.prev=head;
        map=new HashMap<>();
    }

    public int get(int key){
        Node node=map.get(key);
        if(node!=null){
            remove(node);
            addFirst(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key,int value){
        //if exist
        Node node=map.get(key);
        if(node!=null){
            node.value=value;
            remove(node);
            addFirst(node);
        }//if capacity reached
        else {
            if (map.size() == capacity) {
                remove(tail.prev);
                map.remove(tail.prev.key);
            }
            Node n=new Node(key,value);
            addFirst(n);
            map.put(key,n);
        }
        //if not exist

    }

    private void addFirst(Node node) {
        node.next=head.next;
        node.prev=head;
        head.next=node;
        head.next.prev=node;
    }

    private void remove(Node node) {

        node.next.prev=node.prev;
        node.prev.next=node.next;
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2); // Capacity of 2

        lruCache.put(1, 1); // cache is {1=1}
        lruCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(lruCache.get(1));    // returns 1, cache is {2=2, 1=1}

        lruCache.put(3, 3); // evicts key 2, cache is {1=1, 3=3}
        System.out.println(lruCache.get(2));    // returns -1 (not found)

        lruCache.put(4, 4); // evicts key 1, cache is {3=3, 4=4}
        System.out.println(lruCache.get(1));    // returns -1 (not found)
        System.out.println(lruCache.get(3));    // returns 3
        System.out.println(lruCache.get(4));    // returns 4
    }
}
