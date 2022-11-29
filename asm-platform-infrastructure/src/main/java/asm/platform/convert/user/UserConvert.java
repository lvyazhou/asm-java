package asm.platform.convert.user;

import asm.platform.entity.user.UserEntity;
import asm.platform.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserConvert {


    UserConvert MAPPER = Mappers.getMapper( UserConvert.class );

    /**
     * entity to dao
     * @param userEntity
     * @return
     */
    User toUser(UserEntity userEntity);
}
