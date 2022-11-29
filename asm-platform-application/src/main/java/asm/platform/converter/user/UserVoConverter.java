package asm.platform.converter.user;

import asm.platform.entity.user.UserEntity;
import asm.platform.vo.user.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * 用户 entity - vo
 *
 * @author 吕亚洲 <lvyazhou@qq.cn>
 * @date 2022/11/11
 */

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserVoConverter {

    UserVoConverter MAPPER = Mappers.getMapper( UserVoConverter.class );

    /**
     * entity to Vo
     * @param userEntity
     * @return
     */
    UserVo toVo(UserEntity userEntity);

}
