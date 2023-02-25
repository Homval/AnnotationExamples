package ru.khomyakov;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Box box = new Box("box", 4, 5);

        Object result = AnnotationExecutor.execute(box);
        System.out.println(result);

    }


}
