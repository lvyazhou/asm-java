package asm.platform.converter.user;

import asm.platform.entity.user.UserEntity;
import asm.platform.vo.user.UserVo;
import java.math.BigInteger;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-10T09:56:55+0800",
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
