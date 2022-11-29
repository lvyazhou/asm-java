package asm.platform.service.user;

import asm.platform.dto.user.UserDto;
import asm.platform.exception.BllException;
import asm.platform.vo.user.UserVo;

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
}
