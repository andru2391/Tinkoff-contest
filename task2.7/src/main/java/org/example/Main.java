package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static final int MOD = 998244353;

    // Сложение по модулю
    public static int add(int a, int b) {
        a += b;
        if (a >= MOD) {
            a -= MOD;
        }
        return a;
    }

    // Вычитание по модулю
    public static int sub(int a, int b) {
        a -= b;
        if (a < 0) {
            a += MOD;
        }
        return a;
    }

    // Умножение по модулю
    public static int mod(int a, int b) {
        return (int)(((long)a * b) % MOD);
    }

    // Вычисление биномиальных коэффициентов до max_k
    public static int[][] bin_coeff(int max_k) {
        int[][] binom = new int[max_k + 1][];
        for (int i = 0; i <= max_k; i++) {
            binom[i] = new int[i + 1];
        }
        for (int i = 0; i <= max_k; i++) {
            binom[i][0] = 1;
            binom[i][i] = 1;
            for (int j = 1; j < i; j++) {
                binom[i][j] = add(binom[i - 1][j - 1], binom[i - 1][j]);
            }
        }
        return binom;
    }

    public static void result() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine().trim());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(reader.readLine().trim());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int[] S = new int[k + 1];
        S[0] = n % MOD;
        for (int x : a) {
            int current_power = 1;
            for (int t = 1; t <= k; t++) {
                current_power = mod(current_power, x);
                S[t] = add(S[t], current_power);
            }
        }

        int[][] binom = bin_coeff(k);

        int[] two = new int[k + 1];
        for (int i = 0; i <= k; i++) {
            two[i] = 1;
        }
        for (int p = 1; p <= k; p++) {
            two[p] = mod(two[p - 1], 2);
        }
        int inv2 = 499122177;

        List<Integer> results = new ArrayList<>();
        for (int p = 1; p <= k; p++) {
            int firstPart = 0;
            for (int r = 0; r <= p; r++) {
                int term = mod(binom[p][r], S[r]);
                term = mod(term, S[p - r]);
                firstPart = add(firstPart, term);
            }
            firstPart = mod(firstPart, inv2);
            int secondPart = mod(two[p - 1], S[p]);
            int res = sub(firstPart, secondPart);
            results.add(res);
        }

        PrintWriter out = new PrintWriter(System.out);
        for (Integer res : results) {
            out.println(res);
        }
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        result();
    }
}


/*
# Согласно законам неизвестного государства Т каждый совершеннолетний гражданин должен уметь складывать и умножать числа.
        # Дан массив a1, a2, ..., an. Для каждого р от 1 до к рассмотрим следующий процесс:
        # 1) для всех і, j таких, что 1 <= i < j <= n выпишем пары (аi, аj);
        # 2) в полученной последовательности каждую пару заменим на сумму ее элементов;
        # 3) в очередной последовательности каждый элемент возведем в р-ю степень;
        # 4) сложим все числа итоговой последовательности;
        # 5) заменим значение на его остаток при делении на 998244353
        # Обозначим результат за f(p). Найдите значения f(1), f(2)..... f(k).

        # Формат входных данных:
        # Первая строка содержит числа n (2 <= n <= 2 * 10^5) и k (1<=300).
        # Вторая строка содержит числа a1, a2,...an, (1 <= ai <= 10^8).

        # Формат выходных данных:
        # Выведите f(1), f(2),..., f(k), каждое в новой строке.*/
