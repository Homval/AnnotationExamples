package ru.khomyakov.AnnoApp;

import ru.khomyakov.AnnoApp.entities.Box;
import ru.khomyakov.AnnoApp.executors.ResourceAnnotationExecutor;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Box box = new Box("box", 4, 5);

        ResourceAnnotationExecutor.execute(box);

    }
}
