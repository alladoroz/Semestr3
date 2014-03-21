package Alla.Task4;

/**
 * Created by Alla on 08.03.14.
 */
import java.util.Random;

/**
 * основной класс
 */
public class Array {
    private static int numberOfInts;

    /**
     * конструктор
     * @param number число элементов в массиве
     */
    public Array(int number) {
        Array.numberOfInts = number;
    }

    /**
     * подсчет нулевых элементов
     * @param array массив входных данных
     * @return количество нулевых элементов
     */
    public int calculate(int[] array) {
        int numberOfZeros = 0;
        for (int i = 0; i < numberOfInts; i++)
            if (array[i] == 0)
                numberOfZeros++;
        return numberOfZeros;
    }

    private static int calc(int[] array) {
        int numberOfZeros = 0;
        for (int i = 0; i < numberOfInts; i++)
            if (array[i] == 0)
                numberOfZeros++;
        return numberOfZeros;
    }

    /**
     * основной метод
     * @param args
     */
    public static void main(String[] args) {
        numberOfInts = 1000;
        Random random = new Random();

        int array[] = new int[numberOfInts];

        for (int i = 0; i < numberOfInts; i++)
            array[i] = random.nextInt() % 50;

        System.out.println("Number of zeros = " + calc(array));
    }
}