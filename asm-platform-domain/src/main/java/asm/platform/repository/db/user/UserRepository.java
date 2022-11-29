package asm.platform.repository.db.user;

import asm.platform.entity.user.UserEntity;

/**
 * 用户信息 持久化对象方法实现
 *
 * @author lvyazhou@qq.com
 * @date 2022-11-11
 */
public interface UserRepository {

    /**
     * 根据用户名密码查询用户是否存在
     *
     * @param account  账号
     * @param pwd 密码
     * @return 用户信息
     */
    UserEntity findUserByAccountAndPassword(String account, String pwd);

    /**
     * 保存用户信息
     *
     * @param user 用户信息
     * @return
     */
    int saveUser(UserEntity user);
}
