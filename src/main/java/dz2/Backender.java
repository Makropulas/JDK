package dz2;

public class Backender extends Developer implements BackendAction{
    @Override
    public void backend() {
        System.out.println("Я пишу бэк!");
    }

    @Override
    public String toString() {
        return "Бэкендер";
    }
}
