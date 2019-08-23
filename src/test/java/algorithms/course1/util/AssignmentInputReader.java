package algorithms.course1.util;

import algorithms.course1.week4.Graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

public class AssignmentInputReader {

    public static int[] readArrayInput(String filename, int size) throws FileNotFoundException {
        Scanner scanner = getScanner(filename);
        int[] input = new int[size];
        int i = 0;
        while (scanner.hasNextInt()) {
            input[i++] = scanner.nextInt();
        }
        System.out.println("Read input: [" + input[0] + ", ..., " + input[input.length - 1] + "]");
        return input;
    }

    public static Graph readGraph(String filename, String separator) throws FileNotFoundException {
        Scanner scanner = getScanner(filename);
        Graph graph = new Graph();
        while (scanner.hasNextLine()) {
            String[] ids = scanner.nextLine().split(separator);
            String edge1Id = ids[0];
            for (int i = 1; i < ids.length; i++) {
                String edge2Id = ids[i];
                if (!graph.isConnected(edge1Id, edge2Id)) {
                    graph.addVertex(edge1Id, edge2Id);
                }
            }
        }
        System.out.println("Read input graph with " + graph.numberOfEdges() + " edges and " + graph.numberOfVertices() + " vertices");
        return graph;
    }

    private static Scanner getScanner(String filename) throws FileNotFoundException {
        ClassLoader classLoader = AssignmentInputReader.class.getClassLoader();
        URL resourceUrl = classLoader.getResource(filename);
        assert resourceUrl != null;
        File file = new File(resourceUrl.getFile());
        return new Scanner(file);
    }
}
