package leetcode.hashingprefixsum;

import java.util.*;

public class AnagramGroup {

    public static void main(String[] args) {
        String[] str = {"eat", "aet", "tea", "tae", "sat", "tas", "sta", "tui"};
        Map<String, List<String>> map = new HashMap<>();
        List<String> maxgroup = new ArrayList<>();
        for (String s : str) {
            char[] ch = s.toCharArray();
            Arrays.sort(ch);
            String sortedkey = new String(ch);
            //put empty list if  no value
            map.putIfAbsent(sortedkey, new ArrayList<>());
            List<String> list = map.get(sortedkey);
            list.add(s);
            if (list.size() > maxgroup.size()) {
                maxgroup = list;
            }

        }
        System.out.println("Map" + map);
        System.out.println("Max group" + maxgroup);

        //max two groups

        List<List<Integer>> order = new ArrayList<>(str.length);


        // Step 2: Use a Min-Heap (PriorityQueue) to keep track of the top K largest lists
        // We compare lists by their sizes. The smallest of the top K stays at the head.
        PriorityQueue<List<String>> minHeap = new PriorityQueue<>((l1, l2) -> Integer.compare(l2.size(), l1.size()));

        for (List<String> group : map.values()) {
            minHeap.offer(group);
        }
        System.out.println("priority queue" + minHeap);
        int k = 2;
        while (k > 0) {
            System.out.println("priority queue" + minHeap.poll());
            k--;
            // Remove the smallest group to maintain size K
        }
        // Step 3: Extract elements from the heap and reverse them
        // so the largest group comes first
//        List<List<String>> result = new ArrayList<>(minHeap);
//        result.sort((l1, l2) -> Integer.compare(l2.size(), l1.size()));

    }
}
