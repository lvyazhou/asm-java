package asm.platform.service.user.impl;

import asm.platform.assemler.user.UserDtoAssembler;
import asm.platform.common.enums.ErrorCodeEnum;
import asm.platform.converter.user.UserVoConverter;
import asm.platform.dto.user.UserDto;
import asm.platform.dto.user.UserQueryDto;
import asm.platform.entity.user.UserEntity;
import asm.platform.entity.user.UserQueryEntity;
import asm.platform.exception.BllException;
import asm.platform.repository.db.user.UserRepository;
import asm.platform.service.user.UserService;
import asm.platform.vo.user.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

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
        // 判断请求dto是否为空
        if (dto == null)
            throw  new BllException(ErrorCodeEnum.VALID_MAP_ERROR);

        // dto 转化为entity
        UserEntity user = UserDtoAssembler.MAPPER.toEntity(dto);
        user.setUStatus((short) 1);
        user.setCreateTime(new Timestamp(System.currentTimeMillis()));
        user.setCreateUserId(111111111L);
        // 入库
        return this.userRepository.saveUser(user);
    }

    /**
     * 用户查询分页信息
     *
     * @param userQueryDto
     * @return
     */
    @Override
    public List<UserVo> findUserList(UserQueryDto userQueryDto) {
        // 查询dto转化为实体dto
        UserQueryEntity queryEntity = UserDtoAssembler.MAPPER.toQueryEntity(userQueryDto);
        // 用户查询分页列表
        List<UserEntity> userEntityList = this.userRepository.findUserList(queryEntity);
        // 查询用户实体转化为vo
        return UserVoConverter.MAPPER.toVoList(userEntityList);
    }
}
