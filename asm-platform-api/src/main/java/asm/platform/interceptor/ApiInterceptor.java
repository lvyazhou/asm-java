package asm.platform.interceptor;

import asm.platform.utils.ResponseWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: ApiInterceptor <br/>
 * date: 2019年11月21日 下午3:01:49 <br/>
 *
 * @author lvyazhou@360.cn
 */
@Slf4j
@Component
public class ApiInterceptor implements HandlerInterceptor {

    /**
     * 拦截器
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) {
        log.info("api interceptor ...");
//        ResponseWriter.print(httpServletResponse,"error");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
