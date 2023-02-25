package ru.khomyakov;

public class Box {
    private String name;
    private int weight;
    private int amount;

    public Box(String name, int weight, int amount) {
        this.name = name;
        this.weight = weight;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @MyAnno(path = 25)
    public String getValue(int value) {
        return String.format("%s: value = %d", name, value);
    }
}
