package leetcode.dfsbfs;

import java.util.*;

public class DijkstraAlgorithm {

    static class Edge {
        int node;
        int weight;

        Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    static class Pair {
        int node;
        int distance;

        Pair(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static int[] dijkstra(
            int vertices,
            List<List<Edge>> graph,
            int source) {

        int[] dist = new int[vertices];

        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Pair> pq =
            new PriorityQueue<>(
                Comparator.comparingInt(p -> p.distance)
            );

        dist[source] = 0;

        pq.offer(new Pair(source, 0));

        while (!pq.isEmpty()) {

            Pair current = pq.poll();

            int node = current.node;
            int distance = current.distance;

            // Skip outdated queue entries
            if (distance != dist[node]) {
                continue;
            }

            for (Edge edge : graph.get(node)) {

                int newDistance = distance + edge.weight;

                if (newDistance < dist[edge.node]) {

                    dist[edge.node] = newDistance;

                    pq.offer(
                        new Pair(edge.node, newDistance)
                    );
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {

        int vertices = 4;

        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }

        // Undirected graph
        graph.get(0).add(new Edge(1, 4));
        graph.get(1).add(new Edge(0, 4));

        graph.get(0).add(new Edge(2, 1));
        graph.get(2).add(new Edge(0, 1));

        graph.get(2).add(new Edge(1, 2));
        graph.get(1).add(new Edge(2, 2));

        graph.get(1).add(new Edge(3, 1));
        graph.get(3).add(new Edge(1, 1));

        graph.get(2).add(new Edge(3, 5));
        graph.get(3).add(new Edge(2, 5));

        int[] distances = dijkstra(vertices, graph, 0);

        for (int i = 0; i < vertices; i++) {
            System.out.println(
                "Node " + i + " -> " + distances[i]
            );
        }
    }
}
