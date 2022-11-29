package asm.platform.service.asm.impl;

import asm.platform.model.asm.AsmRole;
import asm.platform.repository.id.IdGeneratorWorker;
import asm.platform.repository.mongo.AsmRoleRepository;
import asm.platform.service.asm.AsmRoleService;
import com.mongodb.internal.connection.ClusterDescriptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AsmRoleServiceImpl implements AsmRoleService {
    /**
     * idGeneratorWorker
     */
    @Resource
    public IdGeneratorWorker idGeneratorWorker;

    @Resource
    private AsmRoleRepository asmRoleRepository;

    @Override
    public void saveRole() {
        AsmRole role = new AsmRole();
        role.setId(idGeneratorWorker.nextId());
        role.setRName("role_002");
        this.asmRoleRepository.save(role);
    }

    @Override
    public List<AsmRole> listRoleByPage() {
        Criteria c = Criteria.where("rName").is("role_001");
        Pageable pageable = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "id"));
        Page<AsmRole> roles = this.asmRoleRepository.findAll(pageable);
        System.out.println("total count is " + roles.getTotalElements());
        List<AsmRole> roleList = roles.getContent();
        roleList.forEach((r) -> {
//            System.out.println(r.getId());
//            System.out.println(r.getRName());
        });

        // query sdl
//        Page<AsmRole> roles2 = this.asmRoleRepository.find(c,pageable);
//        System.out.println("total count is " + roles.getTotalElements());
//        List<AsmRole> roleList2 = roles2.getContent();
//        roleList2.forEach((r) -> {
//            System.out.println(r.getId());
//            System.out.println(r.getRName());
//        });
        return roleList;
    }
}
