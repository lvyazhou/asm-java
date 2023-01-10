package asm.platform.convert.user;

import asm.platform.entity.user.UserEntity;
import asm.platform.entity.user.UserQueryEntity;
import asm.platform.model.user.User;
import asm.platform.model.user.UserQuery;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserConvert {


    UserConvert MAPPER = Mappers.getMapper(UserConvert.class);

    /**
     * entity to dao
     *
     * @param userEntity
     * @return
     */
    User toUser(UserEntity userEntity);

    /**
     * query entity to dao
     *
     * @param queryEntity
     * @return
     */
    UserQuery toUserQuery(UserQueryEntity queryEntity);

    /**
     * list dao to entity
     * @param userList
     * @return
     */
    List<UserEntity> toEntityList(List<User> userList);

    /**
     * dao to entity
     * @param user
     * @return
     */
    UserEntity toEntity(User user);
}
