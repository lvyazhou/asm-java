package asm.platform.service.user;

import asm.platform.dto.user.UserDto;
import asm.platform.dto.user.UserQueryDto;
import asm.platform.exception.BllException;
import asm.platform.vo.user.UserVo;

import java.util.List;

/**
 * UserService
 *
 * @author lvyazhou &lt;lvyazhou@360.cn&gt;
 * @date 2022/11/11
 */
public interface UserService {

    /**
     * 用户添加
     *
     * @param dto
     * @return
     */
    int add(UserDto dto) throws BllException;

    /**
     * 用户查询分页列表
     *
     * @param userQueryDto
     * @return
     */
    List<UserVo> findUserList(UserQueryDto userQueryDto);
}
