package org.example;


import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<int[]> coordinates = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            coordinates.add(new int[]{x, y});
        }

        if (n < 3) {
            System.out.println(0);
            return;
        }

        int L = 1;

        for (int i = 0; i < n; i++) {
            Map<String, Integer> slopesCount = new HashMap<>();
            int x1 = coordinates.get(i)[0];
            int y1 = coordinates.get(i)[1];

            for (int j = 0; j < n; j++) {
                if (j == i) {
                    continue;
                }
                int x2 = coordinates.get(j)[0];
                int y2 = coordinates.get(j)[1];
                int dx = x2 - x1;
                int dy = y2 - y1;

                String slope;
                if (dx == 0) {
                    slope = "vert:0";
                } else {
                    int sign = (dx * dy < 0) ? -1 : 1;
                    dx = Math.abs(dx);
                    dy = Math.abs(dy);
                    int g = greatestCommonDivisor(dx, dy);
                    dx /= g;
                    dy /= g;
                    slope = (sign * dy) + ":" + dx;
                }

                slopesCount.put(slope, slopesCount.getOrDefault(slope, 0) + 1);
            }

            int localMax = slopesCount.isEmpty() ? 0 : Collections.max(slopesCount.values());
            L = Math.max(L, localMax + 1);
        }

        if (L <= 2 * n / 3) {
            System.out.println(n / 3);
        } else {
            System.out.println(n - L);
        }
    }

    private static int greatestCommonDivisor(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}

    /*В этом году главой Флатландии является Егор. Всего во Флатландии проживает n людей, каждый в своем доме. i-й дом находится в целочисленной точке (x_i, y_i).
        # Егор может выделить некоторые тройки людей. Требуется лишь, чтобы каждый человек относился не более, чем к одной тройке. Тройка людей считается счастливой, если треугольник, образованный их домами, является невырожденным (то есть три дома не находятся на одной прямой).
        # Помогите Егору определить, какого максимального количества счастливых троек можно добиться во Флатландии.

        # Формат входных данных:
        # Первая строка содержит число n (3 <= n <= 300) — количество жителей во Флатландии.
        # i-я из следующих строчек содержит числа x_i и y_i (-10^9 <= x_i, y_i <= 10^9) — координаты дома, где проживает i-й человек.
        # Гарантируется, что никакие два дома не находятся в одной точке.

        # Формат выходных данных:
        # Выведите одно число — максимальное количество счастливых троек, которого можно добиться во Флатландии.

        # Комментарий к примеру:
        # В примере можно получить две счастливые тройки. Например, подойдет разбиение (1, 1), (2, 2), (1, 4) и (6, 3), (4, 5), (4, 1).
7
1 1
2 2
1 4
6 3
4 5
4 1
3 3
*/
