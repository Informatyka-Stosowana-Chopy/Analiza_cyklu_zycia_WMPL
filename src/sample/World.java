package sample;

import javafx.print.Collation;

import java.util.*;

public class World {

    public enum FightResult {
        DRAW,
        WIN,
        LOSE,
        INVALID
    }

    ;

    private int mapWidth;
    private int mapHeight;
    private List<Level> levels;
    private Player player;
    public List<Enemy> enemies;
    private int currentLevel;
    private int spawnedEnemies;
    private int spawnTimer;
    private Enemy foughtEnemy;


    private void removeDeadEnemies() {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getHealth() <= 0) enemies.remove(i);
        }

    }

    private void spawnEnemies() {
        Random random = new Random();
        if (isPlayerFighting()) {
            return;
        }

        int enemiesInLevel = levels.get(currentLevel).getEnemiesNumber();

        if (spawnedEnemies == enemiesInLevel) {
            return;
        }

        spawnTimer--;

        if (spawnTimer >= 0) {
            return;
        }

        spawnTimer = levels.get(currentLevel).getEnemySpawnFrequency();

        spawnedEnemies++;

        if (spawnedEnemies == enemiesInLevel) {
            int bossHealth = levels.get(currentLevel).getBossHealth();

            if (bossHealth > 0) {
                spawnEnemy(new Boss(bossHealth, new Coordinates(random.nextInt(mapWidth), 0)));
                return;
            }
        }

        spawnEnemy(new Zombie(1, new Coordinates(random.nextInt(mapWidth), 0)));
    }

    private void doEnemiesTurn() {
        if (foughtEnemy != null) {
            return;
        }

        for (Enemy enemy : enemies) {
            if (
                    enemy.getPosition().getY() == mapHeight - 1 ||
                            (enemy.getPosition().getY() == mapHeight - 2 &&
                                    enemy.getPosition().getX() == player.getPosition().getX())
            ) {

                player.setHealth(player.getHealth() - enemy.getHealth());
                enemy.setHealth(0);

            } else {
                moveUnit(enemy, 0, 1);
            }
        }

        removeDeadEnemies();
    }

    public World(int mapWidth, int mapHeight) {
        this.levels = new ArrayList<Level>();
        this.currentLevel = 0;
        this.spawnedEnemies = 0;
        this.spawnTimer = 0;
        this.enemies = new ArrayList<Enemy>();
        this.foughtEnemy = null;
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        this.player = new Player(10, new Coordinates(this.mapWidth / 2, this.mapHeight - 1));
    }

    public void addLevel(Level level) {
        levels.add(level);
    }

    public int getWidth() {
        return mapWidth;
    }

    public int getHeight() {
        return mapHeight;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public int getLevelCount() {
        return levels.size();
    }

    public boolean isLevelComplete() {
        return spawnedEnemies == levels.get(currentLevel).getEnemiesNumber() &&
                getAliveEnemiesNumber() == 0;
    }

    public void setCurrentLevel(int level) {
        if (level < 0 || level >= levels.size()) {
            System.out.println("Invalid level");
        }

        currentLevel = level;
        spawnedEnemies = 0;
        spawnTimer = 0;
    }

    public void moveUnit(Unit unit, int dx, int dy) {
        Coordinates newPosition = new Coordinates(
                unit.getPosition().getX() + dx,
                unit.getPosition().getY() + dy);

        if (newPosition.getX() < 0 || newPosition.getX() >= mapWidth ||
                newPosition.getY() < 0 || newPosition.getY() >= mapHeight) {
            return;
        }
        unit.setPosition(newPosition);
    }


    public Unit getUnitAt(Coordinates position) {
        if (player.getPosition().getX() == position.getX() &&
                player.getPosition().getY() == position.getY()) {
            return player;
        }

        for (int i = 0; i < enemies.size(); i++) {

            if (enemies.get(i).getHealth() <= 0) {
                continue;
            }

            if (enemies.get(i).getPosition().getX() == position.getX() &&
                    enemies.get(i).getPosition().getY() == position.getY()) {
                return enemies.get(i);
            }
        }

        return null;
    }

    public List<Enemy> getEnemiesAtColumn(int x) {
        List<Enemy> foundEnemies = new ArrayList<Enemy>();
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getHealth() <= 0) {
                continue;
            }

            if (enemies.get(i).getPosition().getX() == x) {
                foundEnemies.add(enemies.get(i));
            }
        }

        return foundEnemies;
    }

    public Enemy getEnemyInFront() {
        List<Enemy> enemiesInLine = getEnemiesAtColumn(player.getPosition().getX());

        if (enemiesInLine.isEmpty()) {
            return null;
        }

        Enemy frontEnemy = enemiesInLine.get(0);
        for (int i = 1; i < enemiesInLine.size(); i++) {
            if (enemiesInLine.get(i).getPosition().getY() > frontEnemy.getPosition().getY())
                frontEnemy = enemiesInLine.get(i);
        }

        return frontEnemy;
    }

    public FightResult getFightResult(Unit.Attack playerAttack, Unit.Attack enemyAttack) {
        switch (playerAttack) {
            case PAPER: {

                if (enemyAttack == Unit.Attack.PAPER) {
                    return FightResult.DRAW;
                } else if (enemyAttack == Unit.Attack.SCISSORS) {
                    return FightResult.LOSE;
                } else if (enemyAttack == Unit.Attack.STONE) {
                    return FightResult.WIN;
                }
            }
            case STONE: {

                if (enemyAttack == Unit.Attack.STONE) {
                    return FightResult.DRAW;
                } else if (enemyAttack == Unit.Attack.PAPER) {
                    return FightResult.LOSE;
                } else if (enemyAttack == Unit.Attack.SCISSORS) {
                    return FightResult.WIN;
                }
            }
            case SCISSORS: {

                if (enemyAttack == Unit.Attack.SCISSORS) {
                    return FightResult.DRAW;
                } else if (enemyAttack == Unit.Attack.STONE) {
                    return FightResult.LOSE;
                } else if (enemyAttack == Unit.Attack.PAPER) {
                    return FightResult.WIN;
                }
            }
            default:
                return null;
        }

    }

    public void spawnEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy) {

        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i) == enemy || enemy == getFoughtEnemy()) enemies.remove(i);
        }
        //TODO all function // chyba zrobione xd

//        if(it == enemies.end()) {
//            return;
//        }
//
//        enemies.remove(it);
//
//        if(foughtEnemy == enemy) {
//            foughtEnemy = null;
//        }
    }

    public int getAliveEnemiesNumber() {
        return enemies.size();
    }

    public int getRemainingEnemiesNumber() {
        return levels.get(currentLevel).getEnemiesNumber() - spawnedEnemies;

    }

    public Player getPlayer() {
        return player;
    }

    public Enemy getFoughtEnemy() {
        return foughtEnemy;
    }

    public boolean isPlayerFighting() {
        return foughtEnemy != null;
    }

    public void doPlayerMove(Player.Move move) {
        switch (move) {
            case LEFT: {
                moveUnit(player, -1, 0);
                break;
            }
            case RIGHT: {
                moveUnit(player, 1, 0);
                break;
            }
            case ATTACK: {
                foughtEnemy = getEnemyInFront();
                break;
            }
            default: {
                break;
            }
        }
    }

    public FightResult doPlayerFight() {
        if (foughtEnemy == null) {
            return FightResult.INVALID;
        }

        foughtEnemy.decideNextAttack();

        FightResult fightResult = getFightResult(player.getAttack(), foughtEnemy.getAttack());

        switch (fightResult) {
            case DRAW: {
                break;
            }
            case WIN: {
                foughtEnemy.setHealth(foughtEnemy.getHealth() - 1);
                if (foughtEnemy.getHealth() <= 0) {
                    foughtEnemy = null;
                    player.setHealth(player.getHealth() + 1);
                }
                break;
            }
            case LOSE: {
                player.setHealth(player.getHealth() - 1);
                break;
            }
        }

        return fightResult;
    }

    public String getWorldDisplay() {
        String display = "";

        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                Unit enemy = getUnitAt(new Coordinates(x, y));
                if (enemy == null) {
                    display += ".";
                } else {
                    display += enemy.getSprite();
                }
            }
            display += "\n";
        }

        display += "\nPoziom: " + String.valueOf(currentLevel + 1) +
                "\nHP gracza: " + String.valueOf(player.getHealth()) +
                "\nPozostalo przeciwnikow: " + String.valueOf(getRemainingEnemiesNumber()) +
                '\n';

        return display;
    }

    public void reset() {
        currentLevel = 0;
        spawnedEnemies = 0;
        spawnTimer = 0;
        enemies.clear();
        foughtEnemy = null;
        player = new Player(10, new Coordinates(mapWidth / 2, mapHeight - 1));
    }

    public void step() {
        doEnemiesTurn();
        spawnEnemies();
    }
}



