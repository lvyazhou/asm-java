package asm.platform.repository.db.user;

import asm.platform.convert.user.UserConvert;
import asm.platform.entity.user.UserEntity;
import asm.platform.entity.user.UserQueryEntity;
import asm.platform.mapper.user.UserMapper;
import asm.platform.model.user.User;
import asm.platform.model.user.UserQuery;
import asm.platform.repository.BaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    /**
     * 根据用户名密码查询
     *
     * @param account 账号
     * @param pwd     密码
     * @return
     */
    @Override
    public UserEntity findUserByAccountAndPassword(String account, String pwd) {
        return null;
    }


    /**
     * 保存用户信息
     *
     * @param user 用户信息
     * @return
     */
    @Override
    public int saveUser(UserEntity user) {
        user.setId(idGeneratorWorker.nextId());
        User userDao = UserConvert.MAPPER.toUser(user);
        return this.userMapper.insert(userDao);
    }

    /**
     * 查询分页列表
     *
     * @param queryEntity 查询实体
     * @return
     */
    @Override
    public List<UserEntity> findUserList(UserQueryEntity queryEntity) {
        // 实体转化为model
        UserQuery userQuery = UserConvert.MAPPER.toUserQuery(queryEntity);
        // 查询分页
        List<User> userList = this.userMapper.listByPage(userQuery);
        // user to entity list
        return UserConvert.MAPPER.toEntityList(userList);
    }
}
