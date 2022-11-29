package asm.platform.convert.asm;

import asm.platform.entity.asm.AsmUserEntity;
import asm.platform.model.asm.AsmUser;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AsmUserConvert {


    AsmUserConvert MAPPER = Mappers.getMapper( AsmUserConvert.class );

    /**
     * entity to dao
     * @param userEntity
     * @return
     */
    AsmUser toAsmUser(AsmUserEntity userEntity);
}
