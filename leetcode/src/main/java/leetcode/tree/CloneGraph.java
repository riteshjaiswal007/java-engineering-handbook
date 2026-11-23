package leetcode.tree;

import java.util.*;
import java.util.stream.Collectors;

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<>();
    }

    public Node(int val) {
        this.val = val;
        neighbors = new ArrayList<>();
    }

    public Node(int val, List<Node> neighbors) {
        this.val = val;
        this.neighbors = neighbors;
    }
}

public class CloneGraph {

    public Node cloneGraph(Node node) {

        if (node == null) {
            return null;
        }

        // Original node -> Cloned node
        Map<Node, Node> visited = new HashMap<>();

        Queue<Node> queue = new LinkedList<>();

        // Clone starting node
        Node cloneStart = new Node(node.val);

        visited.put(node, cloneStart);
        queue.offer(node);

        // BFS traversal
        while (!queue.isEmpty()) {

            Node current = queue.poll();

            for (Node neighbor : current.neighbors) {

                // Create clone if not already created
                if (!visited.containsKey(neighbor)) {

                    Node cloneNeighbor =
                        new Node(neighbor.val);

                    visited.put(neighbor, cloneNeighbor);

                    queue.offer(neighbor);
                }

                // Connect cloned current node
                // to cloned neighbor
                visited.get(current).neighbors.add(
                    visited.get(neighbor)
                );
            }
        }

        return cloneStart;
    }

    public static void main(String[] args) {

        // Create original graph
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node1.neighbors.add(node2);
        node1.neighbors.add(node4);

        node2.neighbors.add(node1);
        node2.neighbors.add(node3);

        node3.neighbors.add(node2);
        node3.neighbors.add(node4);

        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        CloneGraph solution = new CloneGraph();

        Node clonedGraph = solution.cloneGraph(node1);

        System.out.println("Original node: " + node1.val);
        System.out.println("Cloned node: " + clonedGraph.val);

        System.out.println(
            "Same object? " + (node1 == clonedGraph)
        );

        System.out.println("Cloned neighbors: " +
                clonedGraph.neighbors.stream()
                        .map(n -> n == null ? null : n.val)
                        .collect(Collectors.toList())
        );
    }
}