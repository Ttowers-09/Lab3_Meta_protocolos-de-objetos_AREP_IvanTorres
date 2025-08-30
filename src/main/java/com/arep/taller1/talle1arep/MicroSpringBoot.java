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
            Object controllerInstance = controllerClass.getDeclaredConstructor().newInstance();
            java.lang.reflect.Method[] methods = controllerClass.getDeclaredMethods();
            for (java.lang.reflect.Method method : methods) {
                if (method.isAnnotationPresent(GetMapping.class)) {
                    GetMapping mapping = method.getAnnotation(GetMapping.class);
                    String path = mapping.value();
                    System.out.println("Registrando endpoint: " + path  + method.getName());
                    
                    HttpServer.get(path, (req, res) -> {
                        try {
                           
                            Object result = method.invoke(controllerInstance);
                            return result != null ? result.toString() : "";
                        } catch (Exception e) {
                            e.printStackTrace();
                            return "Error interno";
                        }
                    });
                }
            }
            HttpServer.main(new String[]{});
        } else {
            System.out.println("No se encuentra");
        }
    }
}
