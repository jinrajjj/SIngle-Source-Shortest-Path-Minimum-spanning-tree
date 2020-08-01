package edu.uncc.cci.algods;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

class MinimumSpanningTree {
    private int index;
    private int vtxCount;
    private Path[] finalResult;
    private String sequence;
    private Path[] edges;

    MinimumSpanningTree() {
        sequence = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    }

    void readFile(String filePath) throws Exception {
        FileReader reader = new FileReader(new File(filePath));
        Scanner scan = new Scanner(reader);
        vtxCount = scan.nextInt();
        int edge = scan.nextInt();
        System.out.println("\nNumber of vertices = " + vtxCount);
        System.out.println("Number of edges = " + edge);
        System.out.println("\nApplying Kruskal's Algorithm:");
        scan.next().charAt(0);
        edges = new Path[edge];
        for (int i = 0; i < edge; ++i) {
            edges[i] = new Path();
        }
        for (int index = 0; index < edge; index++) {
            int source = sequence.indexOf(scan.next().charAt(0));
            int destination = sequence.indexOf(scan.next().charAt(0));
            int weight = scan.nextInt();
            edges[index].source = source;
            edges[index].destination = destination;
            edges[index].weight = weight;
            System.out.println(sequence.charAt(source) + " -> " + sequence.charAt(destination) + " = " + weight);
        }
    }


    private int find(Holder[] sHolder, int i) {
        if (sHolder[i].p != i)
            sHolder[i].p = find(sHolder, sHolder[i].p);
        return sHolder[i].p;
    }

    private void union(Holder[] s, int x, int y) {
        int value1 = find(s, x);
        int value2 = find(s, y);
        if (s[value1].q < s[value2].q)
            s[value1].p = value2;
        else if (s[value1].q > s[value2].q)
            s[value2].p = value1;
        else {
            s[value2].p = value1;
            s[value1].q++;
        }
    }

    void kruskalAlgorithm() {
        finalResult = new Path[vtxCount];
        index = 0;
        int index;
        for (index = 0; index < vtxCount; ++index) {
            finalResult[index] = new Path();
        }
        Arrays.sort(edges);
        Holder[] s = new Holder[vtxCount];
        for (index = 0; index < vtxCount; ++index) {
            s[index] = new Holder();
        }
        for (int v = 0; v < vtxCount; ++v) {
            s[v].p = v;
            s[v].q = 0;
        }
        index = 0;
        while (this.index < vtxCount - 1) {
            Path next_edge;
            next_edge = edges[index++];
            int x = find(s, next_edge.source);
            int y = find(s, next_edge.destination);
            if (x != y) {
                finalResult[this.index++] = next_edge;
                union(s, x, y);
            }
        }
    }

    void displayOutput() {
        int totalCost = 0;
        System.out.println("\nMinimum Spanning Tree:");
        for (int i = 0; i < index; ++i) {
            System.out.println((sequence.charAt(finalResult[i].source)) + " -> " + sequence.charAt(finalResult[i].destination) + " = " + finalResult[i].weight);
            totalCost = totalCost + finalResult[i].weight;
        }
        System.out.println("\nTotal Cost = " + totalCost);
    }

    static class Path implements Comparable<Path> {
        int source;
        int destination;
        int weight;

        public int compareTo(Path compareEdge) {
            return this.weight - compareEdge.weight;
        }
    }

    static class Holder {
        int p;
        int q;
    }
}