package ru.khomyakov;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationExecutor {
    public static Object execute(Object obj) throws InvocationTargetException, IllegalAccessException {
        Method[] declaredMethods = obj.getClass().getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(MyAnno.class)) {
                MyAnno anno = method.getAnnotation(MyAnno.class);
//                Parameter[] parameters = method.getParameters();
//                Type parameterizedType = parameters[0].getParameterizedType();
                return method.invoke(obj, anno.path());
            }
        }
        return null;

    }
}
