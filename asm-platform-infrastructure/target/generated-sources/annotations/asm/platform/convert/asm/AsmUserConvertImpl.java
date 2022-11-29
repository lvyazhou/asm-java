package asm.platform.convert.asm;

import asm.platform.entity.asm.AsmUserEntity;
import asm.platform.model.asm.AsmUser;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-29T10:26:42+0800",
    comments = "version: 1.5.0.Beta2, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class AsmUserConvertImpl implements AsmUserConvert {

    @Override
    public AsmUser toAsmUser(AsmUserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        AsmUser asmUser = new AsmUser();

        asmUser.setId( userEntity.getId() );
        asmUser.setUName( userEntity.getUName() );

        return asmUser;
    }
}
