package edu.uncc.cci.algods;

import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String filePath = null;
        System.out.println("Select the input file from 1, 2, 3, 4, 5 or 6.\nNote: File 1, 2 , 3 and 4 have Undirected Graphs and file 5 and 6  have Directed Graphs.");
        long beginTime, endTime;
        int fileNumber = Integer.parseInt(scanner.nextLine());
        if (fileNumber == 1) {
            filePath = "src/edu/uncc/cci/algods/input01.txt";
        } else if (fileNumber == 2) {
            filePath = "src/edu/uncc/cci/algods/input02.txt";
        } else if (fileNumber == 3) {
            filePath = "src/edu/uncc/cci/algods/input03.txt";
        } else if (fileNumber == 4) {
            filePath = "src/edu/uncc/cci/algods/input04.txt";
        } else if (fileNumber == 5) {
            filePath = "src/edu/uncc/cci/algods/input05.txt";
        } else if (fileNumber == 6) {
            filePath = "src/edu/uncc/cci/algods/input06.txt";
        } else {
            System.out.println("Wrong input!");
            exit(0);
        }
        while (true) {
            System.out.println("\nChoose an action:\n1. Display Shortest Path (using Dijkstra's Algorithm) \n2. Display Minimal Spanning Tree (using Kruskal's Algorithm)\n3. Exit");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    ShortestPath shortestPath = new ShortestPath();
                    shortestPath.readFile(filePath);
                    beginTime = System.nanoTime();
                    shortestPath.dijkstraAlgorithm();
                    endTime = System.nanoTime();
                    shortestPath.displayOutput();
                    System.out.println("\n\nTotal time taken = " + (endTime - beginTime) + " ns");
                    break;
                case 2:
                    MinimumSpanningTree minimumSpanningTree = new MinimumSpanningTree();
                    minimumSpanningTree.readFile(filePath);
                    beginTime = System.nanoTime();
                    minimumSpanningTree.kruskalAlgorithm();
                    endTime = System.nanoTime();
                    minimumSpanningTree.displayOutput();
                    System.out.println("\nTotal time taken = " + (endTime - beginTime) + " ns");
                    break;
                default:
                    exit(0);
            }
        }
    }
}

