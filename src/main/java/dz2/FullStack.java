package dz2;

public class FullStack extends Developer implements BackendAction, FrontAction {
    @Override
    public void backend() {
        System.out.println("Бэк - могу!");
    }

    @Override
    public void frontend() {
        System.out.println("Фронт - могу!");
    }

    @Override
    public String toString() {
        return "Фуллстек";
    }
}
