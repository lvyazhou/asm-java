package asm.platform.service.asm.impl;

import asm.platform.model.asm.AsmDept;
import asm.platform.model.asm.AsmRole;
import asm.platform.repository.id.IdGeneratorWorker;
import asm.platform.repository.mongo.AsmDeptRepository;
import asm.platform.service.asm.AsmDeptService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AsmDeptServiceImpl implements AsmDeptService {
    /**
     * idGeneratorWorker
     */
    @Resource
    public IdGeneratorWorker idGeneratorWorker;

    @Resource
    private AsmDeptRepository asmDeptRepository;

    @Override
    public void saveDept() {
        AsmDept dept = new AsmDept();
        dept.setId(idGeneratorWorker.nextId());
        dept.setDName("dept_001");
        this.asmDeptRepository.save(dept);
    }

    @Override
    public Object listDept() {
        //自定义条件查询
        //定义条件匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                // 类似于 =
                .withMatcher("id", ExampleMatcher.GenericPropertyMatchers.exact())
                // 类似于like
                .withMatcher("dName", ExampleMatcher.GenericPropertyMatchers.contains());
        //条件值对象
        AsmDept dept2 = new AsmDept();
        dept2.setDName("dept_00");
        dept2.setId(161409603982066688L);
        Example<AsmDept> example = Example.of(dept2, exampleMatcher);

        // 分页
        Pageable pageable = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "id"));

        Page<AsmDept> depts = this.asmDeptRepository.findAll(example, pageable);
        System.out.println("total count is " + depts.getTotalElements());
        List<AsmDept> roleList2 = depts.getContent();
        roleList2.forEach((r) -> {
            System.out.println(r.getId());
            System.out.println(r.getDName());
        });

        return depts;
    }
}
