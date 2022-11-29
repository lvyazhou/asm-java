package asm.platform.service.asm;

import asm.platform.entity.asm.AsmUserEntity;

public interface AsmUserService {

    void saveAsmUser(AsmUserEntity userEntity);

    /**
     * 两表联查
     * @return
     */
    Object findStudentAndGrade();

    Object findList();
}
