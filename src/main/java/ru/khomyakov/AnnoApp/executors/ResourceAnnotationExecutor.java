package ru.khomyakov.AnnoApp.executors;

import ru.khomyakov.AnnoApp.annotations.Resource;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class ResourceAnnotationExecutor {
    public static void execute(Object obj) throws InvocationTargetException, IllegalAccessException {
        Method[] declaredMethods = obj.getClass().getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(Resource.class)) {
                Resource anno = method.getAnnotation(Resource.class);
                String path = anno.path();
                List<String> params = getParamsFromProperty(path);
                if (params != null) {
                    for (String param : params) {
                        Object[] methodParams = getMethodParams(method, param);
                        executeMethod(method, obj, methodParams);
                    }
                } else {
                    executeMethod(method, obj, null);
                }
            }
        }
    }

    private static void executeMethod(Method method, Object obj, Object[] methodParams) throws InvocationTargetException, IllegalAccessException {
        Class<?> returnType = method.getReturnType();
        if(returnType == void.class) {
            method.invoke(obj, methodParams);
            System.out.println(method.getName() + " is invoked");
        } else {
            System.out.println(method.invoke(obj, methodParams));
        }
    }

    private static Object[] getMethodParams(Method method, String param) {
        String[] paramsArray = param.split(" ");
        Object[] castedParamsArray = new Object[paramsArray.length];
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length == 0){
            return null;
        }
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> parameterType = parameterTypes[i];
            if (parameterType == String.class) {
                castedParamsArray[i] = paramsArray[i];
            } else if (parameterType == int.class || parameterType == Integer.class) {
                castedParamsArray[i] = Integer.parseInt(paramsArray[i]);
            } else {
                try {
                    Constructor<?> constructor = parameterType.getConstructor(String.class);
                    castedParamsArray[i] = constructor.newInstance(paramsArray[i]);
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }

            }
        }
        return castedParamsArray;
    }

    private static List<String> getParamsFromProperty(String path) {
        List<String> params = new ArrayList<>();
        String pathToParams = "";
        try(InputStream is = new FileInputStream("src/main/resources/myApp.properties")) {
            Properties properties = new Properties();

            properties.load(is);
            pathToParams = properties.getProperty(path);

        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

        if (pathToParams == null) return null;

        try(BufferedReader reader = new BufferedReader(new FileReader(pathToParams))) {
            String line = reader.readLine();

            while (line != null && !line.equals("")) {
//                System.out.println(line);
                params.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return params;
    }
}
