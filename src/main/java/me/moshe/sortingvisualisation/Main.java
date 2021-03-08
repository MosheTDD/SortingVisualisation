package me.moshe.sortingvisualisation;

import me.moshe.sortingvisualisation.ui.Interface;

public class Main {

    private static Interface inf = new Interface();
    public static void main(String[] args) {
        inf.start(args);
    }

    public static void bubbleSort(int[] a){
        int n = a.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (a[j] > a[j+1])
                {
                    // swap arr[j+1] and arr[j]
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
    }
    private static void printArray(int[] a){
        int n = a.length;
        for (int j : a) System.out.print(j + " ");
        System.out.println();
    }
}
