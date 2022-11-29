package asm.platform.convert.user;

import asm.platform.entity.user.UserEntity;
import asm.platform.model.user.User;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-29T08:37:38+0800",
    comments = "version: 1.5.0.Beta2, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class UserConvertImpl implements UserConvert {

    @Override
    public User toUser(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( userEntity.getId() );
        user.account( userEntity.getAccount() );
        user.email( userEntity.getEmail() );
        user.tel( userEntity.getTel() );
        user.createTime( userEntity.getCreateTime() );
        user.createUserId( userEntity.getCreateUserId() );
        user.updateTime( userEntity.getUpdateTime() );
        user.updateUserId( userEntity.getUpdateUserId() );

        return user.build();
    }
}
