package asm.platform.repository.mongo;

import asm.platform.model.asm.AsmRole;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AsmRoleRepository extends PagingAndSortingRepository<AsmRole, Long> {
}
