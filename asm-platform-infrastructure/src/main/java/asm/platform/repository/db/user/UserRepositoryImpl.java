package asm.platform.repository.db.user;

import asm.platform.convert.user.UserConvert;
import asm.platform.entity.user.UserEntity;
import asm.platform.mapper.user.UserMapper;
import asm.platform.model.user.User;
import asm.platform.repository.BaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户信息 持久化对象方法实现
 *
 * @author lvyazhou@qq.com
 * @date 2022-11-11
 */
@Slf4j
@Service
public class UserRepositoryImpl extends BaseRepository implements UserRepository {
    /**
     * userMapper
     */
    @Resource
    private UserMapper userMapper;

    @Override
    public UserEntity findUserByAccountAndPassword(String account, String pwd) {
        return null;
    }

    @Override
    public int saveUser(UserEntity user) {
        user.setId(idGeneratorWorker.nextId());
        User userDao = UserConvert.MAPPER.toUser(user);
        return this.userMapper.insert(userDao);
    }
}
