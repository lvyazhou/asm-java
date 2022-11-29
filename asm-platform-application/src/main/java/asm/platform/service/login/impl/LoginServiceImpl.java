package asm.platform.service.login.impl;

import asm.platform.dto.login.LoginDto;
import asm.platform.exception.NoDataException;
import asm.platform.service.login.LoginService;
import asm.platform.vo.login.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 登录服务类
 *
 * @author 吕亚洲 <lvyazhou@qq.cn>
 * @date 2022/11/11
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public LoginVo login(LoginDto dto) throws NoDataException {
        // 判断是否为空
        if (dto == null)
            throw new NoDataException("login dto is null");

        // 返回实体
        return LoginVo.builder()
                .account(dto.getAccount())
                .build();
    }
}
