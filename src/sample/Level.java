package sample;

public class Level {
    private int enemiesNumber;
    private int enemySpawnFrequency;
    private int bossHealth;


    public Level(int enemiesNumber, int enemySpawnFrequency, int bossHealth) {
        this.enemiesNumber = enemiesNumber;
        this.enemySpawnFrequency = enemySpawnFrequency;
        this.bossHealth = bossHealth;
    }

    public int getEnemiesNumber() {
        return enemiesNumber;
    }

    public int getEnemySpawnFrequency() {
        return enemySpawnFrequency;
    }

    public int getBossHealth() {
        return bossHealth;
    }
}
