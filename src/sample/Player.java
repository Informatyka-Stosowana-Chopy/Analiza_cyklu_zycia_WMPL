package sample;

public class Player extends Unit {
    public enum Move {
        INVALID,
        LEFT,
        RIGHT,
        ATTACK
    }

    ;

    public Player(int health, Coordinates position) {
        super(health, position);
    }

    public char getSprite() {
        return 'P';
    }
}
