package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] daysAndGoodDays = scanner.nextLine().split(" ");
        int n = Integer.parseInt(daysAndGoodDays[0]);
        int m = Integer.parseInt(daysAndGoodDays[1]);
        int[] a = new int[n];

        String[] eachDayDistance = scanner.nextLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(eachDayDistance[i]);
        }

        int a1 = a[0];
        int a2 = a[1];

        ArrayList<Integer> adjustments = new ArrayList<>();
        int goodDays = 0;

        for (int i = 2; i < n; i++) {
            if (a1 <= a[i] && a[i] <= a2) {
                goodDays++;
            } else {
                if (a[i] < a1) {
                    adjustments.add(a1 - a[i]);
                } else if (a[i] > a2) {
                    adjustments.add(a[i] - a2);
                }
            }
        }

        if (goodDays >= m) {
            System.out.println(0);
        } else {
            Collections.sort(adjustments);
            int result = 0;
            for (int i = 0; i < m - goodDays; i++) {
                result = result + adjustments.get(i);
            }
            System.out.println(result);
        }
        scanner.close();
    }
}