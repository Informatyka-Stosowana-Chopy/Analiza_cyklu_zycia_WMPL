package game;

public class Zombie extends Enemy {
    public Zombie(int health, Coordinates position) {
        super(health, position);
    }

    public char getSprite() {
        return 'Z';
    }
}
