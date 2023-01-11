package asm.platform.convert.user;

import asm.platform.entity.user.UserEntity;
import asm.platform.entity.user.UserQueryEntity;
import asm.platform.model.user.User;
import asm.platform.model.user.UserQuery;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-11T10:17:05+0800",
    comments = "version: 1.5.0.Beta2, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class UserConvertImpl implements UserConvert {

    @Override
    public User toUser(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        User user = new User();

        user.setId( userEntity.getId() );
        user.setAccount( userEntity.getAccount() );
        user.setEmail( userEntity.getEmail() );
        user.setUName( userEntity.getUName() );
        user.setTel( userEntity.getTel() );
        user.setUPwd( userEntity.getUPwd() );
        user.setUStatus( userEntity.getUStatus() );
        user.setCreateTime( userEntity.getCreateTime() );
        user.setCreateUserId( userEntity.getCreateUserId() );
        user.setUpdateTime( userEntity.getUpdateTime() );
        user.setUpdateUserId( userEntity.getUpdateUserId() );

        return user;
    }

    @Override
    public UserQuery toUserQuery(UserQueryEntity queryEntity) {
        if ( queryEntity == null ) {
            return null;
        }

        UserQuery userQuery = new UserQuery();

        return userQuery;
    }

    @Override
    public List<UserEntity> toEntityList(List<User> userList) {
        if ( userList == null ) {
            return null;
        }

        List<UserEntity> list = new ArrayList<UserEntity>( userList.size() );
        for ( User user : userList ) {
            list.add( toEntity( user ) );
        }

        return list;
    }

    @Override
    public UserEntity toEntity(User user) {
        if ( user == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( user.getId() );
        userEntity.setAccount( user.getAccount() );
        userEntity.setEmail( user.getEmail() );
        userEntity.setUName( user.getUName() );
        userEntity.setTel( user.getTel() );
        userEntity.setUPwd( user.getUPwd() );
        userEntity.setUStatus( user.getUStatus() );
        userEntity.setCreateTime( user.getCreateTime() );
        userEntity.setCreateUserId( user.getCreateUserId() );
        userEntity.setUpdateTime( user.getUpdateTime() );
        userEntity.setUpdateUserId( user.getUpdateUserId() );

        return userEntity;
    }
}
