package dz2;

public class Main {
    public static void main(String[] args) {
        Developer[] team = {
                new Frontender(),
                new Backender(),
                new FullStack()
        };

        System.out.println("Можешь писать фронт?\n");
        for (Developer developer : team) {
            System.out.printf("%s: ", developer);
            if (developer instanceof FrontAction)
                ((FrontAction) developer).frontend();
            else System.out.println("Не умею. Это не моё!");
        }
    }
}
