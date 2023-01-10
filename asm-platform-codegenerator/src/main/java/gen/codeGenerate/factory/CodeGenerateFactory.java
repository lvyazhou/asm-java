package gen.codeGenerate.factory;

import gen.codeGenerate.CommonPageParser;
import gen.codeGenerate.CreateBean;
import gen.codeGenerate.def.CodeResourceUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;

import java.util.Map;

public class CodeGenerateFactory {
    private static final Log log = LogFactory.getLog(CodeGenerateFactory.class);
    private static String url = CodeResourceUtil.URL;
    private static String username = CodeResourceUtil.USERNAME;
    private static String passWord = CodeResourceUtil.PASSWORD;

    private static String buss_package = CodeResourceUtil.bussiPackage;
    private static String projectPath = getProjectPath();

    public static void codeGenerate(String tableName, String codeName, String controllerEntityPackage, String keyType) {
        codeGenerate(tableName, codeName, "", controllerEntityPackage, keyType);
    }

    public static void codeGenerate(String tableName, String codeName, String entityPackage,
                                    String controllerEntityPackage, String keyType) {
        CreateBean createBean = new CreateBean();
        createBean.setMysqlInfo(url, username, passWord);

        String className = createBean.getTablesNameToClassName(tableName);
        String lowerName = className.substring(0, 1).toLowerCase() + className.substring(1);

        String pckPath = "";//srcPath + CodeResourceUtil.bussiPackageUrl + "\\";

        String entityPath = (entityPackage == null || "".equals(entityPackage)) ? "" : entityPackage + "\\";
        String basePath = "D:/asm-platform-api/src/main/java/asm/platform/";

        String beanPath = basePath + "model/" + entityPath + className + ".java";
        String mapperPath = basePath + "dao/" + entityPath + className + "Mapper.java";
        String servicePath = basePath + "service/" + entityPath + "I" + className + "Service.java";
        String serviceImplPath = basePath + "service/impl/" + entityPath + className + "ServiceImpl.java";
        String controllerPath = basePath + "controller/" + entityPath + className + "Controller.java";
        String sqlMapperPath = basePath + "dao/mapping/" + entityPath + className + "Mapper.xml";


        VelocityContext context = new VelocityContext();
        context.put("className", className);
        context.put("lowerName", lowerName);
        context.put("codeName", codeName);
        context.put("tableName", tableName);
        context.put("bussPackage", buss_package);
        context.put("entityPackage", entityPackage == "" ? null : entityPackage);
        context.put("controllerEntityPackage", controllerEntityPackage == "" ? null : controllerEntityPackage);
        context.put("keyType", keyType);
        String tableNameSimple = createBean.getTablesNameToDb(tableName); //根据表名称，获取表注释中文名
        //实体生成
        try {
            context.put("tableNameSimple", tableNameSimple);
            context.put("feilds", createBean.getBeanFeilds(tableName));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //SQL生成
        try {
            Map sqlMap = createBean.getAutoCreateSql(tableName);
            context.put("columnDatas", createBean.getColumnDatas(tableName));
            context.put("SQL", sqlMap);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        //serviceName生成,service路径生成
//		try {
//			Map sqlMap = createBean.getAutoCreateSql(tableName);
//			context.put("serviceDatas", createBean.getColumnDatas(tableName));
//			context.put("SQL", sqlMap);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return;
//		}				

        //list.ftl中行实体展示
        try {
            context.put("heads", createBean.getHtmlHeads(tableName, lowerName));
            context.put("items", createBean.getHtmlItems(tableName, lowerName));
            context.put("pageInclude", createBean.getIncludePage());
            context.put("titleInclude", createBean.getIncludeTitle());
            context.put("headInclude", createBean.getIncludeHead());

            context.put("whereItems", createBean.getHtmlWhereItems(tableName, lowerName));
            context.put("formInfo", createBean.getFormTextInfo(tableName, lowerName));
            context.put("formViewInfo", createBean.getFormViewInfo(tableName, lowerName));
            context.put("addUrl", "/" + lowerName + "/add");
            context.put("updateUrl", "/" + lowerName + "/update");

            context.put("ruler", createBean.getRulerViewInfo(tableName, lowerName));

        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("----------------------------生成开始---------------------------");
        CommonPageParser.WriterPage(context, "EntityTemplate.ftl", pckPath, beanPath);
        CommonPageParser.WriterPage(context, "DaoTemplate.ftl", pckPath, mapperPath);
        CommonPageParser.WriterPage(context, "ServiceTemplate.ftl", pckPath, servicePath);
        CommonPageParser.WriterPage(context, "ServiceImplTemplate.ftl", pckPath, serviceImplPath);
        CommonPageParser.WriterPage(context, "MapperTemplate.xml", pckPath, sqlMapperPath);
        CommonPageParser.WriterPage(context, "ControllerTemplate.ftl", pckPath, controllerPath);
//		CommonPageParser.WriterPage(context, "list.ftl", webPath, "list.ftl");
//		CommonPageParser.WriterPage(context, "add.ftl", webPath, "add.ftl");
//		CommonPageParser.WriterPage(context, "detail.ftl", webPath, "detail.ftl");

        //生成
//		CommonPageParser.WriterPage(context, "dubbo-web.xml", pckPath, sqlMapperPath);
//		CommonPageParser.WriterPage(context, "dubbo-service.xml", pckPath, sqlMapperPath);


        log.info("----------------------------生成结束---------------------------");
    }

    public static String getProjectPath() {
        String path = System.getProperty("user.dir").replace("\\", "/") + "/";
        return path;
    }
}