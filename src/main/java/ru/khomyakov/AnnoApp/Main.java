package ru.khomyakov.AnnoApp;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Box box = new Box("box", 4, 5);

        AnnotationExecutor.execute(box);


    }


}
