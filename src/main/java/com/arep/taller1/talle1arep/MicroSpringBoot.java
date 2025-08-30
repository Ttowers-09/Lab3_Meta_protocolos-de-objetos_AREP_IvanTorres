package com.arep.taller1.talle1arep;

public class MicroSpringBoot {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Debe proporcionar el nombre de la clase del controlador como argumento.");
            return;
        }
        String controllerClassName = args[0];
        Class<?> controllerClass = Class.forName(controllerClassName);
        if (controllerClass.isAnnotationPresent(RestController.class)) {
            System.out.println("Controlador: " + controllerClassName);
            
            java.lang.reflect.Method[] methods = controllerClass.getDeclaredMethods();
            for (java.lang.reflect.Method method : methods) {
                if (method.isAnnotationPresent(GetMapping.class)) {
                    GetMapping mapping = method.getAnnotation(GetMapping.class);
                    System.out.println("Endpoint detectado: " + mapping.value() + method.getName());
                }
            }
        } else {
            System.out.println("No se encuentra");
        }
    }
}
