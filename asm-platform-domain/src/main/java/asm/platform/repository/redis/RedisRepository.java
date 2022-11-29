package asm.platform.repository.redis;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by lvyz
 * 1，StringRedisTemplate继承了RedisTemplate。
 * 2，RedisTemplate是一个泛型类，而StringRedisTemplate则不是。
 * 3，StringRedisTemplate只能对key=String，value=String的键值对进行操作，RedisTemplate可以对任何类型的key-value键值对操作
 * 4，是他们各自序列化的方式不同，但最终都是得到了一个字节数组，殊途同归，StringRedisTemplate使用的是StringRedisSerializer类；
 * RedisTemplate使用的是JdkSerializationRedisSerializer类。反序列化，则是一个得到String，一个得到Object
 */

public interface RedisRepository {
    boolean set(final String key, Object value);

    boolean set(final String key, Object value, Long expireTime, TimeUnit timeUnit);

    void remove(final String... keys);

    void removePattern(final String pattern);

    void remove(final String key);

    boolean exists(final String key);

    Object get(final String key);

    String getString(final String key);

    void hmSet(String key, Object hashKey, Object value);

    Object hmGet(String key, Object hashKey);

    void lPush(String k, Object v);

    List<Object> lRange(String k, long l, long l1);

    void add(String key, Object value);

    Set<Object> setMembers(String key);

    void zAdd(String key, Object value, double scoure);

    Set<Object> rangeByScore(String key, double scoure, double scoure1);

    boolean tryLock(String lockKey);

    boolean tryLock(String lockKey, long expire, TimeUnit timeUnit);

    boolean releaseLock(String lockKey);

    void unLock(String _key);
}