package asm.platform.controller.user;

import asm.platform.bean.ResponseEntity;
import asm.platform.entity.asm.AsmUserEntity;
import asm.platform.service.asm.AsmDeptService;
import asm.platform.service.asm.AsmRoleService;
import asm.platform.service.asm.AsmUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/asm")
public class AsmUserController {

    @Resource
    private AsmUserService asmUserService;

    @Resource
    private AsmRoleService asmRoleService;

    @Resource
    private AsmDeptService asmDeptService;


    @PostMapping(value = "/save")
    public ResponseEntity save() {
        AsmUserEntity entity = new AsmUserEntity();
        entity.setUName("lvyazhou");
        this.asmUserService.saveAsmUser(entity);
        return null;
    }

    @PostMapping(value = "/list")
    public Object list() {
        return this.asmUserService.findStudentAndGrade();
    }

    @PostMapping(value = "/save_role")
    public ResponseEntity saveRole() {
        this.asmRoleService.saveRole();
        return null;
    }

    @PostMapping(value = "/role_list")
    public Object listRole() {
        return this.asmRoleService.listRoleByPage();
    }

    @PostMapping(value = "/save_dept")
    public ResponseEntity saveDept() {
        this.asmDeptService.saveDept();
        return null;
    }

    @GetMapping(value = "/list_dept")
    public Object listDept() {
        return this.asmDeptService.listDept();
    }
}
