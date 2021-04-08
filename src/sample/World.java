package sample;

import java.util.ArrayList;
import java.util.List;

public class World {
    private int mapWidth;
    private int mapHeight;
    private List<Level> levels;
    private Player player;
    private Enemy enemy;
    private int currentLevel;
    private int spawnedEnemies;
    private int spawnTimer;

    public World() {
        levels = new ArrayList<Level>();
        currentLevel = 0;
        spawnedEnemies = 0;
        spawnTimer = 0;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getSpawnedEnemies() {
        return spawnedEnemies;
    }

    public void setSpawnedEnemies(int spawnedEnemies) {
        this.spawnedEnemies = spawnedEnemies;
    }

    public int getSpawnTimer() {
        return spawnTimer;
    }

    public void setSpawnTimer(int spawnTimer) {
        this.spawnTimer = spawnTimer;
    }
}

