package sample;

public class Unit {
    public enum Attack {
        INVALID,
        PAPER,
        STONE,
        SCISSORS,
    }

    protected int health;
    protected Coordinates possition;
    protected Attack nextAttack;

    public Unit(int health, Coordinates position) {
        this.health = health;
        this.possition = position;
    }


    public void setNextAttack(Attack attack) {
        nextAttack = attack;
    }

    public Attack getAttack() {
        return nextAttack;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int thealth) {
        this.health = thealth;
    }

    public Coordinates getPosition() {
        return this.possition;
    }

    public void setPosition(Coordinates coordinates) {
        this.possition = coordinates;
    }

    public char getSprite() {
        return 0;
    }
}
