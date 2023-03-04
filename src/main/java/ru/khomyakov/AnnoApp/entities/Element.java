package ru.khomyakov.AnnoApp.entities;

public class Element {
    private String name;

    public Element(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Element{" +
                "name='" + name + '\'' +
                '}';
    }
}
