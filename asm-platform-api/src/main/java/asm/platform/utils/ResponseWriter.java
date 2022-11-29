package asm.platform.utils;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ClassName: ResponseWriter <br/>
 * date: 2019年11月18日 上午11:11:11 <br/>
 *
 * @author lvyazhou@360.cn
 */

@Slf4j
public class ResponseWriter {

    /**
     * 输出打印结果
     * @param response
     * @param data
     */
    public static void print(HttpServletResponse response, Object data) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try (PrintWriter writer = response.getWriter()) {
//            String s = GsonUtil.toJson(data);
            writer.print(data);
        } catch (IOException e) {
            log.error("response error", e);
        }
    }
}
