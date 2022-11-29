package asm.platform.config;

import asm.platform.interceptor.ApiInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.util.List;

/**
 * ClassName: AuthConfig <br/>
 * date: 2019年11月18日 上午11:11:11 <br/>
 *
 * @author lvyazhou@360.cn
 */

@SpringBootConfiguration
public class ApiMvcConfig implements WebMvcConfigurer {
    @Resource
    private ApiInterceptor apiInterceptor;

//    @Resource
//    private MagicInterceptor magicInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(EXCLUDE_PATH_ARRAY)
                .excludePathPatterns(SWAGGER_API);

        // 注册内存马
//        registry.addInterceptor(magicInterceptor).addPathPatterns("/**");
    }

    /**
     * 不用鉴权的API
     */
    private static final String[] EXCLUDE_PATH_ARRAY = new String[]{
            //登录接口
            "/v1/login",
            //获取验证码接口
            "/verify-code/get",
            // 登出接口
            "/v1/logout"
    };
    /**
     * 不用鉴权的API
     */
    private static final String[] SWAGGER_API = new String[]{
            "/swagger-ui/**",
            "/webjars/**",
            "/v3/**",
            "/swagger-resources/**",
            "/static/**"
    };

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // swagger配置
        registry.
                addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
                .resourceChain(false);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
    }

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        converters.add(jackson2HttpMessageConverter);
    }
}
