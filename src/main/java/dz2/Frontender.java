package dz2;

public class Frontender extends Developer implements FrontAction{
    @Override
    public void frontend() {
        System.out.println("Я пишу фронт!");
    }

    @Override
    public String toString() {
        return "Фронтендер";
    }
}
