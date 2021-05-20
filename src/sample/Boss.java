package sample;

public class Boss extends Enemy {
    public Boss(int health, Coordinates position) {
        super(health, position);
    }

    public char getSprite() {
        return (char) health;
    }

}
