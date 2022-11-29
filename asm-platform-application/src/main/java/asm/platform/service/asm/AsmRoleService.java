package asm.platform.service.asm;

import asm.platform.model.asm.AsmRole;

import java.util.List;

public interface AsmRoleService {
    void saveRole();

    List<AsmRole> listRoleByPage();
}
