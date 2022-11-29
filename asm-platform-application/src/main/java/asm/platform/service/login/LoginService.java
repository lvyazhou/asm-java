package asm.platform.service.login;

import asm.platform.dto.login.LoginDto;
import asm.platform.exception.NoDataException;
import asm.platform.vo.login.LoginVo;

/**
 * LoginService
 *
 * @author lvyazhou &lt;lvyazhou@360.cn&gt;
 * @date 2020/12/23
 */
public interface LoginService {

    /**
     * 用户登录
     *
     * @param dto
     * @return
     */
    LoginVo login(LoginDto dto) throws NoDataException;
}
