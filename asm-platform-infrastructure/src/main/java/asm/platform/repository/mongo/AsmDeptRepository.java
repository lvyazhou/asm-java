package asm.platform.repository.mongo;

import asm.platform.model.asm.AsmDept;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AsmDeptRepository extends MongoRepository<AsmDept, Long> {
}
