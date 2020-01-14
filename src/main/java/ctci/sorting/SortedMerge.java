package ctci.sorting;

import java.util.Arrays;

/**
 * 10.1 Sorted Merge: You are given two sorted arrays, A and B, where A has a large enough buffer at the
 * end to hold B. Write a method to merge B into A in sorted order
 */
class SortedMerge {

    static void merge(int[] a, int[] b) {
        // shift buffer from end to beginning
        int curA = a.length - b.length - 1;
        int curB = b.length - 1;
        for(int i = a.length - 1; i >= 0; i--) {
            if(curB < 0) {
                break;
            } else if(curA < 0) {
                a[i] = b[curB--];
            } else if(a[curA] > b[curB]) {
                a[i] = a[curA--];
            } else {
                a[i] = b[curB--];
            }
        }
    }

}
