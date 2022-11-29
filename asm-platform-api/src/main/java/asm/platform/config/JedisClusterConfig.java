package asm.platform.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * 生成JedisCluster对象
 *
 */
// TODO 使用时需要放开
//@Configuration
public class JedisClusterConfig {


    @Value("${redis.cluster.hostName}")
    private String hostName;

    @Value("${redis.cluster.password}")
    private String password;

    @Value("${redis.cluster.timeout}")
    private int timeout;

    @Value("${redis.cluster.maxAttempts}")
    private int maxAttempts;

    @Value("${redis.cluster.usePool}")
    private boolean usePool;

    @Bean
    @ConfigurationProperties(prefix = "spring.redis.pool")
    public JedisPoolConfig getRedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        return config;
    }

    /**
     * 注意：
     * 这里返回的JedisCluster是单例的，并且可以直接注入到其他类中去使用
     *
     * @return
     */
    @Bean
    public JedisCluster getJedisCluster() {
        //获取服务器数组(这里要相信自己的输入，所以没有考虑空指针问题)
        String[] serverArray = hostName.split(",");
        Set<HostAndPort> nodes = new HashSet<>();

        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
        }

        if (usePool) {
            JedisPoolConfig config = getRedisPoolConfig();
            return new JedisCluster(nodes, timeout, maxAttempts, config);
        }
        return new JedisCluster(nodes, timeout, maxAttempts);
    }

}