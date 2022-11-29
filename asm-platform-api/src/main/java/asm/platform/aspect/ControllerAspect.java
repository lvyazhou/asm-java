package asm.platform.aspect;
import asm.platform.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制器拦截器（主要用户记录服务消耗时间）
 * @author liuli
 */
@Aspect
@Order(-1)
@Component
@Slf4j
public class ControllerAspect {

    /**
     * 定义拦截规则：拦截*.controller包下面的所有类中，有@RequestMapping注解的方法。
     */
    @Pointcut("execution(* asm.platform.controller..*(..)) && (@annotation(org.springframework.web.bind.annotation.RequestMapping) || @annotation(org.springframework.web.bind.annotation.GetMapping) || @annotation(org.springframework.web.bind.annotation.PostMapping)) ")
    public void controllerMethodPointcut() {
    }

    /**
     * 拦截器具体实现
     *
     * @param pjp
     * @return JsonResult（被拦截方法的执行结果，或需要登录的错误提示。）
     */
    @Around("controllerMethodPointcut()")
    public Object Interceptor(ProceedingJoinPoint pjp) throws Throwable {
        long beginTime = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String uri = request.getRequestURI();
        String paramStr = ServletUtils.getParamMapToStr(request);
        Object result = null;
        result = pjp.proceed();
        long costMs = System.currentTimeMillis() - beginTime;
        log.info("URI:【{}】 request params:【{}】 consuming:【{}ms】",uri,paramStr,costMs);
        return result;
    }

}
