package asm.platform.service.asm.impl;

import asm.platform.convert.asm.AsmUserConvert;
import asm.platform.entity.asm.AsmUserEntity;
import asm.platform.model.asm.AsmDept;
import asm.platform.model.asm.AsmRole;
import asm.platform.model.asm.AsmUser;
import asm.platform.repository.db.user.UserRepository;
import asm.platform.repository.id.IdGeneratorWorker;
import asm.platform.repository.mongo.AsmUserRepository;
import asm.platform.service.asm.AsmUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AsmUserServiceImpl implements AsmUserService {
    /**
     * idGeneratorWorker
     */
    @Resource
    public IdGeneratorWorker idGeneratorWorker;

    /**
     * userRepository
     */
    @Resource
    private AsmUserRepository asmUserRepository;


    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void saveAsmUser(AsmUserEntity userEntity) {
        AsmUser user = AsmUserConvert.MAPPER.toAsmUser(userEntity);
        user.setId(idGeneratorWorker.nextId());
        AsmDept dept = new AsmDept();
        dept.setId(idGeneratorWorker.nextId());
        dept.setDName("dept_001");
        user.setDept(dept);
        user.setUName("lvyazhou");
        List<AsmRole> roleList = new ArrayList<AsmRole>() {
            {
                AsmRole role = new AsmRole();
                role.setId(idGeneratorWorker.nextId());
                role.setRName("role_001");
                add(role);

                AsmRole role1 = new AsmRole();
                role1.setId(idGeneratorWorker.nextId());
                role1.setRName("role_002");
                add(role1);
            }
        };
        user.setRoleList(roleList);
        asmUserRepository.save(user);
    }

    @Override
    public Object findStudentAndGrade() {
        return asmUserRepository.findAll();
    }

    public Object findList(){
        return null;
    }
}
