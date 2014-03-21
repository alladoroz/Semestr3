package Alla.zeroElements;

import java.util.Random;

/**
 * основной класс
 */
public class Array {
    /**
     * подсчет нулевых элементов
     * @param array массив входных данных
     * @return количество нулевых элементов
     */
    public int calculate(int[] array) {
        int numberOfZeros = 0;
        for (int i : array)
            if (i == 0)
                numberOfZeros++;
        return numberOfZeros;
    }

    /**
     * подсчёт нулевых элементов в методе main
     * @param array массив входных данных
     * @return количество нулевых элементов
     */
    private static int calc(int[] array) {
        int numberOfZeros = 0;
        for (int i : array)
            if (i == 0)
                numberOfZeros++;
        return numberOfZeros;
    }

    /**
     * основной метод
     * @param args параметры
     */
    public static void main(String[] args) {
        int numberOfInts = 1000;
        Random random = new Random();

        int array[] = new int[numberOfInts];

        for (int i = 0; i < numberOfInts; i++)
            array[i] = random.nextInt() % 50;

        System.out.println("Number of zeros = " + calc(array));
    }
}