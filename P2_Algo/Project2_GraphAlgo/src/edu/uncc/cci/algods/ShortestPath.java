package edu.uncc.cci.algods;

import java.io.File;

import java.io.FileReader;
import java.util.Scanner;

class ShortestPath {
    private int[][] adjMatrix;
    private int vtxCount;
    private int source;
    private int[] weight;
    private int[] path;
    private String sequence;
    private boolean GT;

    ShortestPath() {
        sequence = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        GT = false;
    }

    void readFile(String filePath) throws Exception {
        FileReader reader = new FileReader(new File(filePath));
        Scanner scanner = new Scanner(reader);
        String str;
        if ((str = scanner.nextLine()) != null) {
            String[] sub = str.split(" ");
            vtxCount = Integer.parseInt(sub[0]);
            System.out.println("\nNumber of vertices = " + vtxCount);
            adjMatrix = new int[vtxCount][vtxCount];
            int edges = Integer.parseInt(sub[1]);
            System.out.println("Number of edges = " + edges);
            if (sub[2].equals("D")) {
                GT = true;
                System.out.println("Selected Graph is Directed.");
            } else {
                System.out.println("Selected Graph is Undirected.");
            }
            for (int index = 0; index < edges; index++) {
                str = scanner.nextLine();
                sub = str.split(" ");
                adjMatrix[sequence.indexOf(sub[0].charAt(0))][sequence.indexOf(sub[1].charAt(0))] = Integer.parseInt(sub[2]);
                if (!GT) {
                    adjMatrix[sequence.indexOf(sub[1].charAt(0))][sequence.indexOf(sub[0].charAt(0))] = Integer.parseInt(sub[2]);
                }
            }
            System.out.println("\nApplying Dijkstra's Algorithm:");
            graph();
            if (scanner.hasNext()) {
                str = scanner.nextLine();
                source = sequence.indexOf(str.charAt(0));
                System.out.println("\nSource of graph is " + str.charAt(0) + "\n");
            } else {
                source = 0;
                System.out.println(sequence.charAt(0) + " will be considered as source\n");
            }
        }
    }


    private void graph() {
        System.out.print(" \t");
        for (int index = 0; index < vtxCount; index++) {
            System.out.print("\t" + sequence.charAt(index));
        }
        System.out.println();
        for (int i = 0; i < vtxCount; i++) {
            System.out.print(sequence.charAt(i) + "\t");
            for (int j = 0; j < vtxCount; j++) {
                System.out.print("\t" + adjMatrix[i][j]);
            }
            System.out.println();
        }
    }

    void dijkstraAlgorithm() {
        weight = new int[vtxCount];
        boolean[] visitNode = new boolean[vtxCount];
        for (int i = 0; i < vtxCount; i++) {
            weight[i] = Integer.MAX_VALUE;
            visitNode[i] = false;
        }
        path = new int[vtxCount];
        weight[source] = 0;
        path[source] = -1;
        for (int i = 0; i < vtxCount; i++) {
            int NV = -1;
            int shortestDistance = Integer.MAX_VALUE;
            for (int v = 0; v < vtxCount; v++) {
                if (!visitNode[v] && weight[v] < shortestDistance) {
                    NV = v;
                    shortestDistance = weight[v];
                }
            }
            if (NV != -1) {
                visitNode[NV] = true;
            }
            for (int v = 0; v < vtxCount; v++) {
                if (NV != -1) {
                    int eDistance = adjMatrix[NV][v];
                    if (eDistance > 0 && ((shortestDistance + eDistance) < weight[v])) {
                        path[v] = NV;
                        weight[v] = shortestDistance + eDistance;
                    }
                }
            }
        }
    }

    void displayOutput() {
        System.out.print("Shortest Path from source:");
        for (int i = 0; i < vtxCount; i++) {
            if (i != source) {
                int v = i;
                StringBuilder str = new StringBuilder();
                while (path[v] != -1) {
                    str.insert(0, " -> " + sequence.charAt(v));
                    v = path[v];
                }
                System.out.print("\n" + sequence.charAt(source) + str + " = ");
                System.out.print(weight[i]);
            }
        }
    }
}