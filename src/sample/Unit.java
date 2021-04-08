package sample;

public class Unit {
    protected int health;
    protected Coordinates possition;
    protected Attack nextAttack;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Coordinates getPossition() {
        return possition;
    }

    public void setPossition(Coordinates possition) {
        this.possition = possition;
    }

    public Attack getNextAttack() {
        return nextAttack;
    }

    public void setNextAttack(Attack nextAttack) {
        this.nextAttack = nextAttack;
    }
}
