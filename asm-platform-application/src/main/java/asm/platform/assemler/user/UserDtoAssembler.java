package asm.platform.assemler.user;

import asm.platform.dto.user.UserDto;
import asm.platform.entity.user.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * 用户实体  sys_user
 *
 * @author lvyazhou@qq.com
 * @date 2022-11-11
 * dto与实体对象的转换
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDtoAssembler {

    UserDtoAssembler MAPPER = Mappers.getMapper(UserDtoAssembler.class);

    /***
     * dto to entity
     * @param userDto
     * @return
     */
    UserEntity toEntity(UserDto userDto);

}
