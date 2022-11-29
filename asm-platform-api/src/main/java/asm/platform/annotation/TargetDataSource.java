package asm.platform.annotation;
import java.lang.annotation.*;

/**
 * @description 数据源切换接口，使用时使用注解 @TargetDataSource(value = "指定要使用的数据源")
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String value();
}