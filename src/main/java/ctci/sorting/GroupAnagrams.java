package ctci.sorting;

import java.util.*;

/**
 * 10.2 Group Anagrams: Write a method to sort an array of strings so that all the anagrams are next to
 * each other.
 */
public class GroupAnagrams {

    static void sortAnagrams(String[] input) {
        Map<String, List<String>> groups = new HashMap<>();
        for (String word : input) {
            String sorted = sortChars(word);
            if (!groups.containsKey(sorted)) {
                groups.put(sorted, new LinkedList<>());
            }
            groups.get(sorted).add(word);
        }
        int i = 0;
        while (i < input.length) {
            for (List<String> group : groups.values()) {
                for (String word : group) {
                    input[i++] = word;
                }
            }
        }
    }

    private static String sortChars(String input) {
        char[] chars = input.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
