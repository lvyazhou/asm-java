package asm.platform.mapper.user;

import asm.platform.model.user.User;
import asm.platform.model.user.UserQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户信息 数据层
 *
 * @author lvyazhou@qq.com
 * @date 2022-04-13
 */
@Mapper
public interface UserMapper {

    /**
     * 批量删除方法
     * @param ids
     * @return
     */
    int deleteByIds(final String[] ids);

    /**
     * 单个删除方法
     * @param ids
     * @return
     */
    int deleteById(final Integer ids);

    /**
     * 插入方法
     * @param record
     * @return
     */
    int insert(final User record);

    /**
     * 批量插入方法
     * @param list
     * @return
     */
    int insertBatch(final List<User> list);

    /**
     * 根据主键查询实体（查看方法）
     * @param id
     * @return
     */
    User queryById(final Integer id);

    /**
     * 更新不为空方法
     * @param record
     * @return
     */
    int updateUUserBySelective(final User record);

    /**
     * 查询方法
     * @param query
     * @return
     */
    List<User> listByPage(final UserQuery query);

    /**
     * 查询方法list
     * @param query
     * @return
     */
    public List<User> getUUserMapList(final User query);
}