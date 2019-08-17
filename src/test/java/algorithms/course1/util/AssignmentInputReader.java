package algorithms.course1.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AssignmentInputReader {

    public int[] readArrayInput(String filename, int size) throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        Scanner scanner = new Scanner(file);
        int[] input = new int[size];
        int i = 0;
        while (scanner.hasNextInt()) {
            input[i++] = scanner.nextInt();
        }
        System.out.println("Read input: [" + input[0] + ", ..., " + input[input.length - 1] + "]");
        return input;
    }

}
