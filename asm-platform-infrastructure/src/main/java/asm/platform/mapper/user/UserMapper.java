package asm.platform.mapper.user;

import asm.platform.model.user.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息 数据层
 *
 * @author lvyazhou@qq.com
 * @date 2022-04-13
 */
@Mapper
public interface UserMapper {

    /**
     * 新增用户
     *
     * @param user 用户信息
     * @return 结果
     */
    int insert(User user);

    int deleteByPrimaryKey(Long id);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}