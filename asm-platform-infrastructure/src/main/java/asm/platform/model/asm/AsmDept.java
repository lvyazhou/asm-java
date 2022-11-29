package asm.platform.model.asm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("asm_dept")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsmDept {
    /**
     * mongo id
     */
    @Id
    private Long id;

    /**
     * 角色名称
     */
    @Field("dept_name")
    private String dName;

}
