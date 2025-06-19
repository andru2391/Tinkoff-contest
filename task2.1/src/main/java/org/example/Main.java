package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] array = scanner.nextLine().split("");
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals("R")) {
                System.out.println("Yes");
                break;
            } else if (array[i].equals("M")) {
                System.out.println("No");
                break;
            }
        }
        scanner.close();
    }
}



/*# Недавно маленький Антон научился читать некоторые буквы! Точнее, он научился читать буквы R, S и M. Кроме того, набор из трех букв R, S, M ему кажется правильным, если в нем символ R находится раньше, чем символ M.
        # Определите, является ли строка s правильной по мнению Антона.

        # Формат входных данных:
        # Дана строка s из трех символов, содержащая один символ R, один символ S и один символ M.

        # Формат выходных данных:
        # Выведите Yes, если символ R находится в строке s раньше, чем символ M. В противном случае выведите No.*/

