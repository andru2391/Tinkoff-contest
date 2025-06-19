package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int x = Integer.parseInt(input[1]);
        int y = Integer.parseInt(input[2]);
        int z = Integer.parseInt(input[3]);
        long[] sequence = new long[n];
        String[] secondInput = scanner.nextLine().split(" ");
        scanner.close();
        for (int i = 0; i < n; i++) {
            sequence[i] = Long.parseLong(secondInput[i]);
        }

        long mX = Long.MAX_VALUE;
        long mY = Long.MAX_VALUE;
        long mZ = Long.MAX_VALUE;
        long mXY = Long.MAX_VALUE;
        long mXZ = Long.MAX_VALUE;
        long mYZ = Long.MAX_VALUE;
        long mXYZ = Long.MAX_VALUE;

        long lcmXY = lowestCommonMultiple(x, y);
        long lcmXZ = lowestCommonMultiple(x, z);
        long lcmYZ = lowestCommonMultiple(y, z);
        long lcmXYZ = lowestCommonMultiple(lcmXY, z);

        for (long ai : sequence) {
            long cX = cost(ai, x);
            long cY = cost(ai, y);
            long cZ = cost(ai, z);
            long cXY = cost(ai, lcmXY);
            long cXZ = cost(ai, lcmXZ);
            long cYZ = cost(ai, lcmYZ);
            long cXYZ = cost(ai, lcmXYZ);

            if (cX < mX) mX = cX;
            if (cY < mY) mY = cY;
            if (cZ < mZ) mZ = cZ;
            if (cXY < mXY) mXY = cXY;
            if (cXZ < mXZ) mXZ = cXZ;
            if (cYZ < mYZ) mYZ = cYZ;
            if (cXYZ < mXYZ) mXYZ = cXYZ;
        }

        long S1 = mXYZ;
        long S2 = Math.min(mXY + mZ, mXYZ);
        long S3 = Math.min(mXZ + mY, mXYZ);
        long S4 = Math.min(mYZ + mX, mXYZ);
        long S5 = mX + mY + mZ;
        long answer = Math.min(Math.min(Math.min(S1, S2), Math.min(S3, S4)), S5);
        System.out.println(answer);
    }


    public static long cost(long ai, long m) {
        long r = ai % m;
        return r == 0 ? 0 : m - r;
    }

    public static long lowestCommonMultiple(long a, long b) {
        return  a / greatestCommonDivisor(a, b) * b;
    }

    public static long greatestCommonDivisor(long a, long b) {
        if (b == 0) return a;
        return greatestCommonDivisor(b,a % b);
    }
}



    /*Герман немного устал. Вот бы кто-нибудь сделал за него домашнее задание...
    Задача, которую необходимо решить Герману, звучит следующим образом. Дана последовательность a1, a2, ... , an и числа x, y, z.
    Разрешается произвольное количество (в том числе ноль) раз выполнить следующую операцию: выбрать произвольное i (1<=i<=n) и увеличить ai на единицу.
    Необходимо, чтобы хотя бы один элемент из последовательности делился на x, хотя бы один элемент делился на y и хотя бы один элемент делился на z. Разрешается, чтобы для разных значений из набора (x, y, z) подходил один и тот же элемент из последовательности.
    Помогите Герману отдохнуть перед сессией и найдите минимальное количество операций, которое необходимо выполнить, чтобы условие стало выполнено.
    Формат входных данных
    Первая строка содержит числа n (1<=n<=2*10^5), x, y и z (1<=x,y,z<=10^6).
    Вторая строка содержит числа a1, a2, ... , an (1<=ai<=10^18).
    Формат выходных данных
    Выведите одно число — минимальное количество операций, которое надо выполнить, чтобы для каждого из чисел x, y, z был хотя бы один элемент в последовательности, кратный данному числу.
    Комментарий к примеру
    В примере можно дважды увеличить a4 и один раз увеличить a5. Тогда на 10 будет делиться a4, на 20 будет делиться a5, на 30 будет делиться a4.
    Примеры данных
    ввод:
    6 10 20 30
    8 17 5 28 39 13
    вывод:
    3*/
