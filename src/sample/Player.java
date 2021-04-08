package sample;

public class Player extends Unit{
    private int score;

    public Player() {
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    boolean isAlive()
    {
        return true;
        //TODO
    }
}
