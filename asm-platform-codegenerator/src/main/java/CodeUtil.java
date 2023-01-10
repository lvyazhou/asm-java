import gen.codeGenerate.def.FtlDef;
import gen.codeGenerate.factory.CodeGenerateFactory;

/**
 * @author 吕亚洲
 */

public class CodeUtil {

    public static void main(String[] args) {
        config();
    }

    private static void config() {
        /** 此处修改成你的 表名 和 中文注释 ***/
        String tableNames = "u_user";//表名称
        String codeName = "用户管理";// 中文注释 当然你用英文也是可以的
        String controllerPackage = "controller";// web action包
        String entityPackage = "";
        String keyType = FtlDef.KEY_TYPE_01;// 主键生成方式 01:UUID 02:自增
        for (String tableName : tableNames.split(",")) {
            CodeGenerateFactory.codeGenerate(tableName, codeName, entityPackage, controllerPackage, keyType);
        }
    }
}
