package com.aplose.aploseframework.ZDEVELOP;

import java.lang.reflect.Field;

public final class developHelper {
        /*
     *  Print an object (each key: value)
     */
    public static void printObject(Object obj, String message) {
        System.out.println("\n\ndevelopHelper Print: " + obj.getClass().getSimpleName() + "\n");
        if(message != null){
            System.out.println("\n\nPrint " + message + "\n");
        }

        Class<?> objClass = obj.getClass();
        Field[] fields = objClass.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                System.out.println(field.getName() + ": " + value);
            } catch (IllegalAccessException e) {
                System.out.println("Unable to access field: " + field.getName() + "\n");
            }
        }
        System.out.println("\n\n\n");

    }
}
