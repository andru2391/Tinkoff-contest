package org.example;


import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] inputArray = new int[n];

        for (int i = 0; i < n; i++) {
            inputArray[i] = scanner.nextInt();
        }

        int border = 60;        //тк 2^60 > 10^18
        long[] powersOfTwoArray = new long[border];

        for (int i = 0; i < 60; i++) {
            powersOfTwoArray[i] = (long) Math.pow(2.0, i);
        }

        int size = (border - 2) * (border - 1) * border / (3 * 2 * 1);
        long[] sumsArray = new long[size];
        int iterator = 0;

        for (int i = 0; i < border - 2; i++) {
            for (int j = i + 1; j < border - 1; j++) {
                for (int k = j + 1; k < border; k ++){
                    sumsArray[iterator] = powersOfTwoArray[i] + powersOfTwoArray[j] + powersOfTwoArray[k];
                    iterator++;
                }
            }
        }

        Arrays.sort(sumsArray);
        int index;

        for (int i = 0; i <  inputArray.length; i++) {
            index = Arrays.binarySearch(sumsArray, inputArray[i]);

            if (index == -1) {
                System.out.println(-1);
            } else if (index < 0){
                index = Math.abs(index) - 2;
                System.out.println(sumsArray[index]);
            } else {
                System.out.println(sumsArray[index]);
            }
        }
        scanner.close();
    }
}


/*# В самый что ни на есть обычный день Борис решил, что на протяжении следующих дней он будет покупать своей маме букет ровно из трех цветов. Там, где живет Борис, существует всего лишь один магазин цветов, но зато в нем широкий ассортимент: для каждого i от 0 до 10^100 в магазине есть ровно один уникальный цветок, стоящий 2^i бурлей. К тому же в магазин ежедневно довозят цветы, которые были выкуплены.
        # В i-й день у Бориса есть ai (i здесь индекс) бурлей, которые он готов потратить на букет. Борис хочет купить как можно более дорогой букет. Для каждого из дней определите, за какую стоимость Борис купит букет, или сообщите, что на его деньги невозможно купить никакой букет из трех цветов.

        # Формат входных данных:
        # Первая строка содержит число n (1<=n<=10^5) — количество дней, в течение которых Борис планирует покупать букеты.
        # i-я из следующих n строк содержит число ai (1<=ai<=10^18) (i здесь индекс) — количество бурлей, которое есть у Бориса в i-й день.

        # Формат выходных данных:
        # Для каждого ai (i здесь индекс) в отдельной строке выведите, сколько бурлей Борис потратит на букет в i-й день, или -1, если он не может купить никакой букет ровно из трех цветов.

        # Комментарий к примеру:
        # в первый день Борис может купить цветы стоимостей 2^1, 2^2 и 2^3;
        # во второй день Борис может купить цветы стоимостей 2^0, 2^1 и 2^6;
        # в третий день Борис не может купить цветы таким образом, чтобы составить букет из трех цветов и уложиться в сумму в 5 бурлей.

        # Примеры данных:
        # Ввод:
        # 3
        # 15
        # 67
        # 5
        # Вывод:
        # 14
        # 67
        # -1*/

