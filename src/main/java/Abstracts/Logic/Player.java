package Abstracts.Logic;

public abstract class Player {
    protected Inventory inventory;

    protected String characterName;
    protected int money;
    protected int health;

    // Getters and Setters
    public Inventory getInventory() {
        return inventory;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void changeMoney(int amount) {
        this.money += amount;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void changeHealth(int amount) {
        this.health += amount;
    }
}
