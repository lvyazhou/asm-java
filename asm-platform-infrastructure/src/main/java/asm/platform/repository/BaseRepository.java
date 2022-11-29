package asm.platform.repository;

import asm.platform.repository.id.IdGeneratorWorker;
import asm.platform.repository.redis.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lvyazhou
 * @date 2022/11/19
 * 仓储（repository）:提供查找和持久化对象的方法
 * 仓储父类
 */
public class BaseRepository {

    /**
     * idGeneratorWorker
     */
    @Autowired
    public IdGeneratorWorker idGeneratorWorker;

    /**
     * redisRepository
     */
    @Autowired
    public RedisRepository redisRepository;

}
