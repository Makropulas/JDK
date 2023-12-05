package dz3;

import java.util.Arrays;

public class ArrayComparison {
    public static <T> boolean compareArrays(T[] arr1, T[] arr2) {
        if (arr1.length == arr2.length) {
            for (int i = 0; i < arr1.length; i++) {
                if (!arr1[i].equals(arr2[i])) return false;
            }
            return true;
        }
        return false;
    }

    public static <T> void printComparisonResults(T[] arr1, T[] arr2) {
        System.out.printf("Сравнение %s%s и %s%s -> ",
                arr1.getClass().getSimpleName(), Arrays.toString(arr1),
                arr2.getClass().getSimpleName(), Arrays.toString(arr2));
        System.out.println(compareArrays(arr1, arr2) ? "Массивы равны" : "Массивы отличаются");
    }

    public static void main(String[] args) {
        Number[] numbers = {1, 2, 3, 4, 5};
        Integer[] integers = {1, 2, 3, 4, 5};
        String[] strings = new String[]{"One", "Two", "Three", "Four", "Five"};

        printComparisonResults(numbers, integers);
        printComparisonResults(numbers, strings);
    }
}
