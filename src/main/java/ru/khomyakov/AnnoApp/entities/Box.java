package ru.khomyakov.AnnoApp.entities;

import ru.khomyakov.AnnoApp.annotations.Resource;
import ru.khomyakov.AnnoApp.annotations.Result;

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

    @Resource(path = "arrayOne")
    public String getValue(Integer intValue, String stringValue, Element element) {
        return name + ": value = " + intValue +" and line = " + stringValue + " and other = " + element;
    }

    @Resource(path = "arrayTwo")
    public String getAnotherValue(Integer intValue, String stringValue, Double element) {
        return name + ": value = " + intValue +" and line = " + stringValue + " and other = " + element;
    }

    @Resource(path = "params")
    @Result(path = "results")
    public int getSum(int x, int y) {
        return x + y;
    }

    @Resource(path = "nothing")
    public void doNothing() {
        System.out.println("Do nothing!");
    }
}
