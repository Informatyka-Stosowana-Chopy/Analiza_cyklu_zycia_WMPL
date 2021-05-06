package sample;

import java.util.Random;

public class Enemy extends Unit {

    public Enemy(int health, Coordinates position) {
        super(health, position);
    }

    public void decideNextAttack() {
        Random random = new Random();
        int a = random.nextInt(2);
        //TODO check if random has range <0;2>
        switch (a) {
            case 0:
                setNextAttack(Attack.SCISSORS);
                break;
            case 1:
                setNextAttack(Attack.STONE);
                break;
            case 2:
                setNextAttack(Attack.PAPER);
                break;
        }
    }

    public char getSprite() {
        return 0;
    }

}
