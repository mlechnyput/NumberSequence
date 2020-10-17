package com.company;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Put the sequence: ");
        String str = input.nextLine();
        if (method1(str) == 0) {
            if (method2(str) == 0) {
                System.out.println("Nothing");
            }
        }
    }

    public static int method1(String str) {
        //метод 1 разбивает строку на числа с равным количеством цифр
        int counter = 0;
        Vector v = new Vector();//вектор для хранения результатов проверки
        for (int k = 1; k < str.length() ; k++) {
            String strTransform = str;
            if (str.length() % k == 0) {
                //каунтер считает сколько раз будет вызываться метод check и сколько элементов будет в векторе
                counter++;
                int[] arr = new int[str.length() / k];
                for (int j = 1; j <= str.length() / k; j++) {
                    String element = strTransform.substring(0, k);
                    strTransform = strTransform.substring(k);
                    arr[j - 1] = Integer.parseInt(element);
                }
                //вызов метода check, результат проверки 0 или 1 заносятся в вектор
                v.add(check(arr));
            }
        }
        int summ = 0;
        for (int i = 0; i < counter; i++)
            //если сумма элементов вектора = 0, значит строка не является последовательностью или не содержит пропущенный эл
            summ += (int) v.get(i);
        if (summ == 0) return 0;
        else return 1;
    }

    public static int check(int[] arra) {
        //проверяет являются ли элементы массива последовательностью
        //флаг1 считает сколько раз в массиве предыдущий эл. меньше следующего на единицу
        int flag1 = 0;
        //флаг2 считает сколько раз в массиве предыдущий эл. меньше следующего на два
        int flag2 = 0;
        int missing = 0;

        for (int i = 0; i < arra.length - 1; i++) {
            if (arra[i + 1] == arra[i] + 2) {
                flag2++;
                missing = arra[i] + 1;
            }
            if (arra[i + 1] == arra[i] + 1) flag1++;
        }
        if (((flag1 == arra.length - 2) && (flag2 == 1))||(arra.length==2 && flag2==1)) {
            System.out.println(missing + " is missing");
            //возвращает 1 если есть пропущенный элемент и все остальные элементы возрастают на 1
            return 1;
        } else return 0;

    }

    public static int method2(String str) {
        //метод 2 разбивает строку на числа с разным количеством цифр. как например 99, 100
        int[] arrLeft, arrRight, general;
        String left, right;
        int l = str.length();
        int d;//длина маленькой строки(количество символов)
        Vector v = new Vector();//вектор для хранения результатов проверки

        int counter = 0;

        //верхний цикл перебирает кол-во цифр в маленьком числе
        for (d = 1; d <= l / 2; d++) {

            int nSmall = (l - (d + 1)) / d;//макимальное число маленьких строк(итераций i)

            for (int i = 1; i <= nSmall; i++) {
                int nBig = (l - i * d) / (d + 1);//количество больших строк (зависит от количества маленьких)
                int lengthOfTheElements = i * d + nBig * (d + 1);//суммарная длина всех компонентов после разделения исходной строки
                if (lengthOfTheElements != l) continue;
                arrLeft = new int[i];
                arrRight = new int[nBig];

                //цикл элементов слева (маленьких)
                for (int j = 1; j <= i; j++) {
                    left = str.substring(d * (j - 1), j * d);
                    arrLeft[j - 1] = Integer.parseInt(left);
                }

                //цикл элементов справа (больших)
                for (int k = 1; k <= nBig; k++) {
                    right = str.substring(i * d + (d + 1) * (k - 1), k * (d + 1) + i * d);
                    arrRight[k - 1] = Integer.parseInt(right);
                }
                //объединяем два массива в один
                general = new int[arrLeft.length + arrRight.length];
                for (int x = 0; x < arrLeft.length; x++) general[x] = arrLeft[x];
                int count = 0;
                for (int x = arrLeft.length; x < general.length; x++) {
                    general[x] = arrRight[count];
                    count++;
                }
                counter++;
                //System.out.println(Arrays.toString(general));
                //вызов метода check, результат проверки 0 или 1 заносятся в вектор
                v.add(check(general));
            }
        }

        int summ = 0;
        for (int i = 0; i < counter; i++)
            //если сумма элементов вектора = 0, значит строка не является последовательностью или не содержит пропущенный эл
            summ += (int) v.get(i);
        if (summ == 0) return 0;
        else return 1;

    }

}