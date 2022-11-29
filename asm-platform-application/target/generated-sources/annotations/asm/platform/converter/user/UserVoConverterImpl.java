package asm.platform.converter.user;

import asm.platform.entity.user.UserEntity;
import asm.platform.vo.user.UserVo;
import java.math.BigInteger;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-29T08:37:44+0800",
    comments = "version: 1.5.0.Beta2, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class UserVoConverterImpl implements UserVoConverter {

    @Override
    public UserVo toVo(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserVo.UserVoBuilder userVo = UserVo.builder();

        if ( userEntity.getId() != null ) {
            userVo.id( BigInteger.valueOf( userEntity.getId() ) );
        }

        return userVo.build();
    }
}
