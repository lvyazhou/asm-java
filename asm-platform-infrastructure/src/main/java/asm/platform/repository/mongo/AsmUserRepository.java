package asm.platform.repository.mongo;

import asm.platform.model.asm.AsmUser;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AsmUserRepository extends PagingAndSortingRepository<AsmUser, Long> {

    /**
     * 增加用户
     *
     * @param user
     * @return
     */
//    void insertAsmUserInfo(AsmUserEntity user);
//
//    Object findStudentAndGrade();
}
