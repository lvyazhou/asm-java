package asm.platform.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * ClassName: RestTemplateConfig <br/>
 * date: 2019年11月18日 上午11:11:11 <br/>
 *
 * @author lvyazhou@360.cn
 */
@Configuration
@Slf4j
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(clientHttpRequestFactory());
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
        try {
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

            //开始设置连接池
            PoolingHttpClientConnectionManager poolingHttpClientConnectionManager
                    = new PoolingHttpClientConnectionManager();
            poolingHttpClientConnectionManager.setMaxTotal(100);  //最大连接数
            poolingHttpClientConnectionManager.setDefaultMaxPerRoute(30);  //同路由并发数
            httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager);

            HttpClient httpClient = httpClientBuilder.build();
            // httpClient连接配置
            HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
                    = new HttpComponentsClientHttpRequestFactory(httpClient);
            clientHttpRequestFactory.setConnectTimeout(30 * 1000);  //连接超时
            clientHttpRequestFactory.setReadTimeout(5 * 60 * 1000);     //数据读取超时时间
            clientHttpRequestFactory.setConnectionRequestTimeout(30 * 1000);  //连接不够用的等待时间
            return clientHttpRequestFactory;
        } catch (Exception e) {
            log.error("初始化clientHttpRequestFactory出错", ExceptionUtils.getMessage(e));
        }
        return null;
    }
}
