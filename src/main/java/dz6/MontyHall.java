package dz6;

import java.util.*;

public class MontyHall {
    public static void main(String[] args) {
        Game game = new Game(1000);
        game.startGames();

        double percentChangesChoice = ((double) (game.statistics.countVinChangesChoice) / game.statistics.totalGame) * 100;
        System.out.println("Процент победы по стратегии изменения выбора " + percentChangesChoice);

        double percentNotChangesChoice = ((double) (game.statistics.countVinNotChangesChoice) / game.statistics.totalGame) * 100;
        System.out.println("Процент победы по стратегии сохранения выбора " + percentNotChangesChoice);
    }
}

class Player {
    public int selectedElement;
    public int discountItem;
}

class Game {
    Player changesChoice;
    Player notChangesChoice;

    List<Room> firstPlayerList;
    List<Room> secondPlayerList;

    int countGame;
    static Random random = new Random();

    public Statistics statistics = new Statistics();


    public Game(int count) {
        countGame = count;
        statistics.totalGame = count;
        firstPlayerList = initRoom(count);
        secondPlayerList = initRoom(count);
    }

    public List<Room> initRoom(int count) {
        List<Room> rooms = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            rooms.add(new Room());
        }
        return rooms;
    }

    public void startGames() {
        for (int i = 0; i < countGame; i++) {
            changesChoice = new Player();
            notChangesChoice = new Player();
            changesChoice.selectedElement = random.nextInt(3);
            notChangesChoice.selectedElement = random.nextInt(3);

            changesChoice.discountItem = getGoatInRoom(firstPlayerList.get(i), changesChoice.selectedElement);
            notChangesChoice.discountItem = getGoatInRoom(secondPlayerList.get(i), notChangesChoice.selectedElement);

            changesChoice.selectedElement = anotherChoice(changesChoice);

            if (isWinner(changesChoice, firstPlayerList.get(i))) {
                statistics.countVinChangesChoice++;
            }

            if (isWinner(notChangesChoice, secondPlayerList.get(i))) {
                statistics.countVinNotChangesChoice++;
            }
        }
    }

    public boolean isWinner(Player player, Room room) {
        int vinIndex = room.doors.indexOf(Items.CAR);
        return player.selectedElement == vinIndex;
    }

    public int anotherChoice(Player player) {
        return Arrays.stream(new int[]{0, 1, 2})
                .filter(x -> x != player.discountItem && x != player.selectedElement)
                .findFirst()
                .getAsInt();
    }

    public int getGoatInRoom(Room room, int discount) {
        List<Integer> allGoatsIndex = new ArrayList<>();
        allGoatsIndex.add(room.doors.stream()
                .filter(x -> x == Items.GOAT)
                .map(x -> room.doors.indexOf(x))
                .findFirst()
                .get());
        List<Integer> finalAllGoatsIndex = allGoatsIndex;
        allGoatsIndex.add(room.doors.stream()
                .filter(x -> x == Items.GOAT)
                .map(x -> room.doors.lastIndexOf(x))
                .filter(x -> !x.equals(finalAllGoatsIndex.get(0)))
                .findFirst()
                .get());
        allGoatsIndex = allGoatsIndex.stream()
                .filter(x -> x != null && x != discount)
                .toList();

        int count = allGoatsIndex.size();
        return allGoatsIndex.get(random.nextInt(count));
    }
}

class Room {
    public List<Items> doors;
    static Random random = new Random();

    public Room() {
        int doorNumber = random.nextInt(3);
        doors = new ArrayList<>(List.of(Items.GOAT, Items.GOAT, Items.GOAT));
        doors.set(doorNumber, Items.CAR);
    }
}

class Statistics {
    public int totalGame;
    public int countVinChangesChoice = 0;
    public int countVinNotChangesChoice = 0;
}

enum Items {
    CAR,
    GOAT
}