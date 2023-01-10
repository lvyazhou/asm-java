package asm.platform.service.user.impl;

import asm.platform.assemler.user.UserDtoAssembler;
import asm.platform.dto.user.UserDto;
import asm.platform.entity.user.UserEntity;
import asm.platform.exception.BllException;
import asm.platform.repository.db.user.UserRepository;
import asm.platform.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;

/**
 * UserService
 *
 * @author lvyazhou &lt;lvyazhou@360.cn&gt;
 * @date 2022/11/11
 */

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    /**
     * userRepository
     */
    @Resource
    private UserRepository userRepository;

    /**
     * 用户增加
     *
     * @param dto
     * @return
     * @throws BllException
     */
    @Override
    public int add(UserDto dto) throws BllException {
        UserEntity user = UserDtoAssembler.MAPPER.toEntity(dto);
        user.setUStatus((short) 1);
        user.setCreateTime(new Timestamp(System.currentTimeMillis()));
        user.setCreateUserId(111111111L);
        return this.userRepository.saveUser(user);
    }
}
