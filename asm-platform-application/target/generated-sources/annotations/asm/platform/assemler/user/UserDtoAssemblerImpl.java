package asm.platform.assemler.user;

import asm.platform.dto.user.UserDto;
import asm.platform.entity.user.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-10T09:56:55+0800",
    comments = "version: 1.5.0.Beta2, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class UserDtoAssemblerImpl implements UserDtoAssembler {

    @Override
    public UserEntity toEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setAccount( userDto.getAccount() );
        userEntity.setEmail( userDto.getEmail() );
        userEntity.setUName( userDto.getUName() );
        userEntity.setTel( userDto.getTel() );
        userEntity.setUPwd( userDto.getUPwd() );

        return userEntity;
    }
}
