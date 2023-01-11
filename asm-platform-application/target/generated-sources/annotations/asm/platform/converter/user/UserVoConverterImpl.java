package asm.platform.converter.user;

import asm.platform.entity.user.UserEntity;
import asm.platform.vo.user.UserVo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-11T10:17:10+0800",
    comments = "version: 1.5.0.Beta2, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class UserVoConverterImpl implements UserVoConverter {

    @Override
    public UserVo toVo(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserVo userVo = new UserVo();

        userVo.setId( userEntity.getId() );
        userVo.setAccount( userEntity.getAccount() );
        userVo.setEmail( userEntity.getEmail() );
        userVo.setUName( userEntity.getUName() );

        return userVo;
    }

    @Override
    public List<UserVo> toVoList(List<UserEntity> userEntityList) {
        if ( userEntityList == null ) {
            return null;
        }

        List<UserVo> list = new ArrayList<UserVo>( userEntityList.size() );
        for ( UserEntity userEntity : userEntityList ) {
            list.add( toVo( userEntity ) );
        }

        return list;
    }
}
