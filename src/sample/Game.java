package sample;
import java.util.Scanner;

public class Game {
    private World world;

    private char getUserInput() {
        String userInput = null;
        Scanner scan = new Scanner(System.in);
        userInput = scan.nextLine();

        if (userInput == null) {
            return 0;
        }

        return userInput.charAt(0);
    }

    private Player.Move getPlayerMove(char input) {
        switch (input) {
            case 'a': {
                return Player.Move.LEFT;
            }
            case 'd': {
                return Player.Move.RIGHT;
            }
            case 'w': {
                return Player.Move.ATTACK;
            }
            default: {
                return Player.Move.INVALID;
            }
        }
    }

    private Unit.Attack getPlayerAttack(char input) {
        switch (input) {
            case '1': {
                return Unit.Attack.PAPER;
            }
            case '2': {
                return Unit.Attack.STONE;
            }
            case '3': {
                return Unit.Attack.SCISSORS;
            }
            default: {
                return Unit.Attack.INVALID;
            }
        }
    }

    private void doPlayerTurn() {
        if (world.isPlayerFighting()) {
            Unit.Attack attack = Unit.Attack.INVALID;

            while (attack == Unit.Attack.INVALID) {
                System.out.println("Wybierz atak (1 - GLOCK, 2 - AK-47, 3 - AWP): ");
                attack = getPlayerAttack(getUserInput());
            }

            world.getPlayer().setNextAttack(attack);

            World.FightResult result = world.doPlayerFight();

//        srand(time(NULL));
//        int x = rand()%2;

            switch (result) {
                case DRAW:
                    System.out.println("Remis\n");
                    break;
                case WIN:
                    System.out.println("Wygrana\n");
                    break;
                case LOSE:
                    System.out.println("Przegrana\n");
                    break;
            }
        } else {
            Player.Move move = Player.Move.INVALID;

            while (move == Player.Move.INVALID) {
                System.out.println("Wybierz akcje (a - ruch w lewo, d - ruch w prawo, w - pojedynek): ");
                move = getPlayerMove(getUserInput());
            }

            world.doPlayerMove(move);
        }
    }

    public Game(int mapWidth, int mapHeight) {
        world = new World(mapWidth, mapHeight);

        world.addLevel(new Level(5, 3, 0));
        world.addLevel(new Level(7, 2, 0));
        world.addLevel(new Level(10, 2, 3));
        world.addLevel(new Level(15, 2, 5));
    }

    public boolean isOver() {
        return
                world.getPlayer().getHealth() <= 0 ||
                        world.getLevelCount() == world.getCurrentLevel();
    }

    public void restart() {
        world.reset();
    }

    public void step() {
        doPlayerTurn();
        world.step();
        if (world.isLevelComplete()) {
            onLevelComplete();
        }
    }

    public String getGameDisplay() {
        return world.getWorldDisplay();
    }

    public void onLevelComplete() {
        int currentLevel = world.getCurrentLevel();
        world.setCurrentLevel(currentLevel + 1);
        System.out.println("Poziom " + currentLevel + " pokonany!\n");

        if (currentLevel != world.getLevelCount()) {
            System.out.println("Wprowadz nazwe pliku do zapisu albo zostaw puste, jesli nie chcesz zapisywac: ");

            String path = null;
            //std::getline(std::cin, path);

            if (path != null) {
                try {
                    save(path);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } else {
            System.out.println("Gratulacje! Ukonczyles gre.\n");
            return;
        }
    }

    public void save(String path) {
        //std::ofstream ofs(path, std::ios::out | std::ios::trunc | std::ios::binary);
        //TODO
//        if (!ofs.is_open()) {
//            throw std::runtime_error("Could not open specified file");
//        }

        int playerHp = world.getPlayer().getHealth(),
                playerX = world.getPlayer().getPosition().getX(),
                currentLevel = world.getCurrentLevel();

//        ofs.write((char*)&currentLevel, sizeof(currentLevel));
//        ofs.write((char*)&playerHp, sizeof(playerHp));
//        ofs.write((char*)&playerX, sizeof(playerX));
    }

    public void load(String path) {
        //std::ifstream ifs(path, std::ios::in | std::ios::binary);
//TODO

//        if (!ifs.is_open()) {
//            throw std::runtime_error("Could not open specified file");
//        }

        int playerHp = 0;
        int playerX = 0;
        int currentLevel = 0;

//        ifs.read((char*)&currentLevel, sizeof(currentLevel));
//        ifs.read((char*)&playerHp, sizeof(playerHp));
//        ifs.read((char*)&playerX, sizeof(playerX));

//        if (!ifs.good()
//                || playerHp <= 0
//                || playerX < 0 || playerX >= world->getWidth()) {
//            throw std::runtime_error("Corrupted save file");
//        }

        world.getPlayer().setHealth(playerHp);
        world.getPlayer().setPosition(new Coordinates(playerX, world.getHeight() - 1));
        world.setCurrentLevel(currentLevel);
    }
}
