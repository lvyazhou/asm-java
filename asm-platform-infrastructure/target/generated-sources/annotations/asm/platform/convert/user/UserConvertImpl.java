package asm.platform.convert.user;

import asm.platform.entity.user.UserEntity;
import asm.platform.model.user.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-10T09:58:31+0800",
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
}
