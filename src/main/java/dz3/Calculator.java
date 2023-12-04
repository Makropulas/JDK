package dz3;

public class Calculator {
    public static <N extends Number> double sum(N num1, N num2) {
        return num1.doubleValue() + num2.doubleValue();
    }

    public static <N extends Number> double subtract(N num1, N num2) {
        return num1.doubleValue() - num2.doubleValue();
    }

    public static <N extends Number> double multiply(N num1, N num2) {
        return num1.doubleValue() * num2.doubleValue();
    }

    public static <N extends Number> double divide(N num1, N num2) {
        return num1.doubleValue() / num2.doubleValue();
    }

    public static void main(String[] args) {
        System.out.println(sum(2, 2.0f));
        System.out.println(subtract(10.0f, 2L));
        System.out.println(multiply(3L, 2.0f));
        System.out.println(divide(10.6, 2));
    }
}
