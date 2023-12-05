package dz3;

public class Pair<T, U> {
    private final T first;
    private final U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return "Пара {" + first + ", " + second + '}';
    }

    public static void main(String[] args) {
        System.out.println(new Pair<>(1, "Первый"));
        System.out.println(new Pair<>("Второй", 2.0));
    }
}
