package gen.codeGenerate;

import gen.codeGenerate.def.CodeResourceUtil;
import gen.codeGenerate.def.CommUtil;
import gen.codeGenerate.def.TableConvert;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class CreateBean {
    private Connection connection = null;
    static String url;
    static String username;
    static String password;
    static String rt = "\r\t";
    String SQLTables = "show tables";
    private String method;
    private String argv;
    static String selectStr = "select ";
    static String from = " from ";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMysqlInfo(String url, String username, String password) {
        CreateBean.url = url;
        CreateBean.username = username;
        CreateBean.password = password;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public List<String> getTables() throws SQLException {
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(SQLTables);
        ResultSet rs = ps.executeQuery();
        List list = new ArrayList();
        while (rs.next()) {
            String tableName = rs.getString(1);
            list.add(tableName);
        }
        rs.close();
        ps.close();
        con.close();
        return list;
    }

    public List<ColumnData> getColumnDatas(String tableName) throws SQLException {
        String SQLColumns = "select column_name ,data_type,column_comment,0,0,character_maximum_length,is_nullable nullable from information_schema.columns where table_name =  '"
                + tableName + "' " + "and table_schema =  '" + CodeResourceUtil.DATABASE_NAME + "'";

        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(SQLColumns);
        List columnList = new ArrayList();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String name = rs.getString(1);
            String type = rs.getString(2);
            String comment = rs.getString(3);
            String precision = rs.getString(4);
            String scale = rs.getString(5);
            String charmaxLength = rs.getString(6) == null ? "" : rs.getString(6);
            String nullable = TableConvert.getNullAble(rs.getString(7));
            type = getType(type, precision, scale);

            ColumnData cd = new ColumnData();
            cd.setColumnName(name);
            cd.setDataType(type);
            cd.setColumnType(rs.getString(2));
            cd.setColumnComment(comment);
            cd.setPrecision(precision);
            cd.setScale(scale);
            cd.setCharmaxLength(charmaxLength);
            cd.setNullable(nullable);
            formatFieldClassType(cd);
            columnList.add(cd);
        }
        argv = "";
        method = "";
        rs.close();
        ps.close();
        con.close();
        return columnList;
    }

    public String getBeanFeilds(String tableName) throws SQLException {
        List<ColumnData> dataList = getColumnDatas(tableName);
        StringBuffer str = new StringBuffer();
        StringBuffer getset = new StringBuffer();
        for (ColumnData d : dataList) {
            String name = CommUtil.formatName(d.getColumnName());
            String type = d.getDataType();
            String comment = d.getColumnComment();

            String maxChar = name.substring(0, 1).toUpperCase();
            str.append("\r\t").append("private ").append(type + " ").append(name).append(";//   ").append(comment);
            String method = maxChar + name.substring(1);
            getset.append("\r\t").append("public ").append(type + " ").append("get" + method + "() {\r\t");
            getset.append("    return this.").append(name).append(";\r\t}");
            getset.append("\r\t").append("public void ").append("set" + method + "(" + type + " " + name + ") {\r\t");
            getset.append("    this." + name + "=").append(name).append(";\r\t}");
        }
        argv = str.toString();
        this.method = getset.toString();
        return argv + this.method;
    }

    private String formatTableName(String name) {
        String[] split = name.split("_");
        if (split.length > 1) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < split.length; i++) {
                String tempName = split[i].substring(0, 1).toUpperCase() + split[i].substring(1);
                sb.append(tempName);
            }

            return sb.toString();
        }
        String tempName = split[0].substring(0, 1).toUpperCase() + split[0].substring(1);
        return tempName;
    }

    private void formatFieldClassType(ColumnData columnt) {
        String fieldType = columnt.getColumnType();
        String scale = columnt.getScale();

        if ("N".equals(columnt.getNullable())) {
            columnt.setOptionType("required:true");
        }
        if (("datetime".equals(fieldType)) || ("time".equals(fieldType))) {
            columnt.setClassType("easyui-datetimebox");
        } else if ("date".equals(fieldType)) {
            columnt.setClassType("easyui-datebox");
        } else if ("int".equals(fieldType)) {
            columnt.setClassType("easyui-numberbox");
        } else if ("number".equals(fieldType)) {
            if ((StringUtils.isNotBlank(scale)) && (Integer.parseInt(scale) > 0)) {
                columnt.setClassType("easyui-numberbox");
                if (StringUtils.isNotBlank(columnt.getOptionType()))
                    columnt.setOptionType(columnt.getOptionType() + "," + "precision:2,groupSeparator:','");
                else
                    columnt.setOptionType("precision:2,groupSeparator:','");
            } else {
                columnt.setClassType("easyui-numberbox");
            }
        } else if (("float".equals(fieldType)) || ("double".equals(fieldType)) || ("decimal".equals(fieldType))) {
            columnt.setClassType("easyui-numberbox");
            if (StringUtils.isNotBlank(columnt.getOptionType()))
                columnt.setOptionType(columnt.getOptionType() + "," + "precision:2,groupSeparator:','");
            else
                columnt.setOptionType("precision:2,groupSeparator:','");
        } else {
            columnt.setClassType("easyui-validatebox");
        }
    }

    public String getType(String dataType, String precision, String scale) {
        dataType = dataType.toLowerCase();
        if (dataType.contains("char") || dataType.contains("text"))
            dataType = "java.lang.String";
        else if (dataType.contains("bit"))
            dataType = "java.lang.Boolean";
        else if (dataType.contains("bigint"))
            dataType = "java.lang.Long";
        else if (dataType.contains("int"))
            dataType = "java.lang.Integer";
        else if (dataType.contains("float"))
            dataType = "java.lang.Float";
        else if (dataType.contains("double"))
            dataType = "java.lang.Double";
        else if (dataType.contains("number")) {
            if ((StringUtils.isNotBlank(scale)) && (Integer.parseInt(scale) > 0))
                dataType = "java.math.BigDecimal";
            else if ((StringUtils.isNotBlank(precision)) && (Integer.parseInt(precision) > 6))
                dataType = "java.lang.Long";
            else
                dataType = "java.lang.Integer";
        } else if (dataType.contains("decimal"))
            dataType = "BigDecimal";
        else if (dataType.contains("date"))
            dataType = "java.util.Date";
        else if (dataType.contains("time"))
            dataType = "java.sql.Timestamp";
        else if (dataType.contains("clob"))
            dataType = "java.sql.Clob";
        else {
            dataType = "java.lang.Object";
        }
        return dataType;
    }

    public void getPackage(int type, String createPath, String content, String packageName, String className, String extendsClassName, String[] importName) throws Exception {
        if (packageName == null) {
            packageName = "";
        }
        StringBuffer sb = new StringBuffer();
        sb.append("package ").append(packageName).append(";\r");
        sb.append("\r");
        for (int i = 0; i < importName.length; i++) {
            sb.append("import ").append(importName[i]).append(";\r");
        }
        sb.append("\r");
        sb.append("/**\r *  entity. @author wolf Date:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\r */");
        sb.append("\r");
        sb.append("\rpublic class ").append(className);
        if (extendsClassName != null) {
            sb.append(" extends ").append(extendsClassName);
        }
        if (type == 1)
            sb.append(" ").append("implements java.io.Serializable {\r");
        else {
            sb.append(" {\r");
        }
        sb.append("\r\t");
        sb.append("private static final long serialVersionUID = 1L;\r\t");
        String temp = className.substring(0, 1).toLowerCase();
        temp = temp + className.substring(1);
        if (type == 1) {
            sb.append("private " + className + " " + temp + "; // entity ");
        }
        sb.append(content);
        sb.append("\r}");
        System.out.println(sb);
        createFile(createPath, "", sb.toString());
    }

    public String getTablesNameToClassName(String tableName) {
        String tempTables = formatTableName(tableName);
        return tempTables;
    }

    //TODO: 通过表获取表名称注释
    public String getTablesNameToDb(String tableName) {
        String resultTableName = "";
        String SQLColumns = "select TABLE_COMMENT as table_name from INFORMATION_SCHEMA.TABLES Where table_schema = 'wms' AND table_name = '" + tableName + "'";
        Connection con = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(SQLColumns);
            while (rs.next()) {
                resultTableName = rs.getString(1);
            }
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        }
        return resultTableName;
    }

    public void createFile(String path, String fileName, String str) throws IOException {
        FileWriter writer = new FileWriter(new File(path + fileName));
        writer.write(new String(str.getBytes(StandardCharsets.UTF_8)));
        writer.flush();
        writer.close();
    }

    public Map<String, Object> getAutoCreateSql(String tableName) throws Exception {
        Map sqlMap = new HashMap();
        List columnDatas = getColumnDatas(tableName);
        String columns = getColumnSplit(columnDatas);
        String formatColumns = getFormatColumnSplit(columnDatas);
        String[] columnList = getColumnList(columns);
        String columnFields = getColumnFields(columns);
        String insert = "insert into " + tableName + "(" + columns.replaceAll("\\|", ",") + ")\n values(#{" + formatColumns.replaceAll("\\|", "},#{") + "})";
        String update = getUpdateSql(tableName, columnList);
        String updateSelective = getUpdateSelectiveSql(tableName, columnDatas);
        String selectById = getSelectByIdSql(tableName, columnList);
        String delete = getDeleteSql(tableName, columnList);
        String deletes = getDeleteSqls(tableName, columnList);

        sqlMap.put("columnList", columnList);
        sqlMap.put("columnFields", columnFields);
        sqlMap.put("insert", insert.replace("#{createTime}", "now()").replace("#{updateTime}", "now()"));
        sqlMap.put("update", update.replace("#{createTime}", "now()").replace("#{updateTime}", "now()"));
        sqlMap.put("delete", delete);
        sqlMap.put("deletes", deletes);
        sqlMap.put("ids", "${id}"); //输出删除批量操作的ID。
        sqlMap.put("updateSelective", updateSelective);
        sqlMap.put("selectById", selectById);
        return sqlMap;
    }

    public String getDeleteSql(String tableName, String[] columnsList) throws SQLException {
        String sb = "delete " +
                "\t from " + tableName + " where " +
                columnsList[0] + " = #{" + CommUtil.formatName(columnsList[0]) + "}";
        return sb;
    }

    public String getDeleteSqls(String tableName, String[] columnsList) throws SQLException {
        String sb = " <![CDATA[ " + "\n" +
                "delete " +
                "\t from " + tableName + " where " +
                columnsList[0] + " in " + "\n" +
                "  ]]>" + "\n";
        return sb;
    }

    public String getSelectByIdSql(String tableName, String[] columnsList) throws SQLException {
        String sb = "select <include refid=\"Base_Column_List\" /> \n" +
                "\t from " + tableName + " where " +
                columnsList[0] + " = #{" + CommUtil.formatName(columnsList[0]) + "}";
        return sb;
    }

    public String getColumnFields(String columns) throws SQLException {
        String fields = columns;
        if ((fields != null) && (!"".equals(fields))) {
            fields = fields.replaceAll("[|]", ",");
        }
        return fields;
    }

    public String[] getColumnList(String columns) throws SQLException {
        String[] columnList = columns.split("[|]");
        return columnList;
    }

    public String getUpdateSql(String tableName, String[] columnsList) throws SQLException {
        StringBuffer sb = new StringBuffer();

        for (int i = 1; i < columnsList.length; i++) {
            String column = columnsList[i];
            if (!"CREATETIME".equalsIgnoreCase(column)) {
                if ("UPDATETIME".equalsIgnoreCase(column))
                    sb.append(column + "=now()");
                else {
                    sb.append(column + "=#{" + CommUtil.formatName(column) + "}");
                }
                if (i + 1 < columnsList.length)
                    sb.append(",");
            }
        }
        String update = "update " + tableName + " set " + sb + " where " + columnsList[0] + "=#{" + CommUtil.formatName(columnsList[0]) + "}";
        return update;
    }

    public String getUpdateSelectiveSql(String tableName, List<ColumnData> columnList) throws SQLException {
        StringBuffer sb = new StringBuffer();
        ColumnData cd = columnList.get(0);
        sb.append("\t<trim  suffixOverrides=\",\" >\n");
        for (int i = 1; i < columnList.size(); i++) {
            ColumnData data = columnList.get(i);
            String columnName = data.getColumnName();
            sb.append("\t<if test=\"").append(CommUtil.formatName(columnName)).append(" != null ");

            if ("String" == data.getDataType()) {
                sb.append(" and ").append(CommUtil.formatName(columnName)).append(" != ''");
            }
            sb.append(" \">\n\t\t");
            sb.append(columnName + "=#{" + CommUtil.formatName(columnName) + "},\n");
            sb.append("\t</if>\n");
        }
        sb.append("\t</trim>");
        String update = "update " + tableName + " set \n" + sb + " where " + cd.getColumnName() + "=#{" + CommUtil.formatName(cd.getColumnName()) + "}";
        return update;
    }

    public String getColumnSplit(List<ColumnData> columnList) throws SQLException {
        StringBuffer commonColumns = new StringBuffer();
        for (ColumnData data : columnList) {
            commonColumns.append(data.getColumnName() + "|");
        }
        return commonColumns.delete(commonColumns.length() - 1, commonColumns.length()).toString();
    }

    public String getFormatColumnSplit(List<ColumnData> columnList) throws SQLException {
        StringBuffer commonColumns = new StringBuffer();
        for (ColumnData data : columnList) {
            commonColumns.append(data.getFormatColumnName() + "|");
        }
        return commonColumns.delete(commonColumns.length() - 1, commonColumns.length()).toString();
    }

    /**
     * 生成list页面，table格式。获取列内容信息
     *
     * @param tableName
     * @return
     * @throws SQLException
     * @author lvyazhou
     */
    public String getHtmlHeads(String tableName, String lowerName) throws SQLException {
        List<ColumnData> dataList = getColumnDatas(tableName);
        StringBuffer returnStr = new StringBuffer();
        returnStr.append("<tr class='table_listTr' >").append("\n ");
        returnStr.append("<th scope='col'><label><input type='checkbox' onclick=\"selectAll(this,'id')\"> 全选</label></th>").append("\n ");
        for (ColumnData d : dataList) {
            String name = d.getColumnComment();
            returnStr.append(" <th width='70px' scope='col'>").append(name).append("</th>").append("\n ");
        }
        returnStr.append(" </tr> ").append("\n ");
        return returnStr.toString();
    }

    /**
     * 生成list页面，table格式。
     *
     * @param tableName
     * @return
     * @throws SQLException
     * @author lvyazhou
     */
    public String getHtmlItems(String tableName, String lowerName) throws SQLException {
        List<ColumnData> dataList = getColumnDatas(tableName);
        StringBuffer returnStr = new StringBuffer();
        returnStr.append("<tr>").append("\n ");
        returnStr.append("<td><input name='id' id='id' type='checkbox' value='${item.id}' /></td>").append("\n ");
        for (ColumnData d : dataList) {
            String name = CommUtil.formatName(d.getColumnName());
            //如果不等于ID的可以增加
            if ("id".equals(name)) {
                returnStr.append("<td>${item_index+1}</td>").append("\n ");
            } else if ("createTime".equals(name) || "updateTime".equals(name)) {
                returnStr.append(" <td width='170px'>").append("${item." + name + "}").append("</td>").append("\n ");
            } else {
                returnStr.append(" <td>").append("${item." + name + "}").append("</td>").append("\n ");
            }
        }
        returnStr.append(" </tr> ").append("\n");
        return returnStr.toString();
    }

    /**
     * 获取列表查询条件中的form表单信息
     *
     * @param tableName
     * @param lowerName
     * @return
     * @throws SQLException
     */
    public String getHtmlWhereItems(String tableName, String lowerName) throws SQLException {
        List<ColumnData> dataList = getColumnDatas(tableName);
        StringBuffer returnStr = new StringBuffer();
        returnStr.append("<!-- 行_start -->").append("\n ");
        for (ColumnData d : dataList) {
            String name = CommUtil.formatName(d.getColumnName());//格式化好的字段名称
            String contextName = d.getColumnComment(); //注释名称
            if (!"id".equals(name) && !name.contains("Author") && !name.contains("Time")) {
                returnStr.append("<!-- 列_start -->").append("\n ");
                returnStr.append("<p>").append("\n ");

                returnStr.append("<span>" + contextName + ":</span>").append("\n ");
                returnStr.append("<input type='text' placeholder='" + contextName + "' name='" + name + "' id='" + name + "' value='${query." + name + "!}' />").append("\n ");

                returnStr.append("</p>").append("\n ");
                returnStr.append("<!-- 列_end -->").append("\n ");
            }
        }
        returnStr.append("<!-- 行_end -->").append("\n ");
        return returnStr.toString();

    }

    /**
     * 获取引用的值
     *
     * @return
     * @author 吕亚洲
     */
    public String getIncludePage() {

        return "<#include " + "'/page/page2.ftl'" + " />";
    }

    public String getIncludeTitle() {
        String returnStr = "<#include " + "'../../includes/head.ftl'" + " />" + "\n " +
                "<#include " + "'../../includes/left.ftl'" + " />" + "\n ";

        return returnStr;
    }

    public String getIncludeHead() {
        return "<#include " + "'../../includes/headjs.ftl'" + " />" + "\n ";
    }

    /**
     * 获取form表单元素信息
     *
     * @return
     * @throws SQLException
     * @author Administrator
     */
    public String getFormTextInfo(String tableName, String lowerName) throws SQLException {
        List<ColumnData> dataList = getColumnDatas(tableName);
        StringBuffer returnStr = new StringBuffer();
        int i = 1;
        for (ColumnData d : dataList) {
            String nameContext = d.getColumnComment();
            String name = CommUtil.formatName(d.getColumnName());
            if (!"id".equals(name) && !name.contains("Author") && !name.contains("Time")) {
                if (i % 3 == 1) {
                    returnStr.append("<!-- 行_start -->").append("\n ");
                    returnStr.append("<div class='row'>").append("\n ");
                }
                returnStr.append("<!-- 列_start -->").append("\n ");
                //需循环部分 开始
                returnStr.append(" <div class='form-group col-sm-4'> ").append("\n ");
                returnStr.append(" <label for='" + name + "' class='col-sm-4 control-label'>" + nameContext + ":</label> ").append("\n ");
                returnStr.append(" <div class='col-sm-6 input-group input-group-sm'> ").append("\n ");
                returnStr.append(" <input type='text' class='form-control' placeholder='" + nameContext + "' name='" + name + "' id='" + name + "' value='${" + lowerName + "." + name + "}' />").append("\n ");
                returnStr.append(" </div>").append("\n");
                returnStr.append(" </div>").append("\n");
                //需循环部分 结束
                returnStr.append(" <!-- 列_end -->").append("\n ");

                if (i % 3 == 0) {
                    returnStr.append(" </div> ").append("\n");
                    returnStr.append("<!-- 行_end -->").append("\n ");
                }
                i++;
            }
        }
        returnStr.append(" <input type='hidden' class='form-control' placeholder='id' name='id' id='id' value='${" + lowerName + ".id}' />").append("\n ");
        return returnStr.toString();
    }

    /**
     * 查看模板增加
     *
     * @return
     * @throws SQLException
     * @author Administrator
     */
    public String getFormViewInfo(String tableName, String lowerName) throws SQLException {
        List<ColumnData> dataList = getColumnDatas(tableName);
        StringBuffer returnStr = new StringBuffer();
        int i = 1;
        for (ColumnData d : dataList) {
            String nameContext = d.getColumnComment();
            String name = CommUtil.formatName(d.getColumnName());
            if (i % 3 == 1) {
                returnStr.append("<!-- 行_start -->").append("\n ");
                returnStr.append("<div class='row'>").append("\n ");
            }
            returnStr.append("<!-- 列_start -->").append("\n ");
            //需循环部分 开始
            returnStr.append(" <div class='form-group col-sm-4'> ").append("\n ");
            returnStr.append(" <label for='" + name + "' class='col-sm-4 control-label'>" + nameContext + ":</label> ").append("\n ");
            returnStr.append(" <div class='col-sm-6 input-group input-group-sm'> ").append("\n ");
            returnStr.append(" ${" + lowerName + "." + name + "}").append("\n ");
            returnStr.append(" </div>").append("\n");
            returnStr.append(" </div>").append("\n");
            //需循环部分 结束
            returnStr.append(" <!-- 列_end -->").append("\n ");

            if (i % 3 == 0) {
                returnStr.append(" </div> ").append("\n");
                returnStr.append("<!-- 行_end -->").append("\n ");
            }
            i++;
        }
        return returnStr.toString();
    }


    /**
     * 校验规则增加
     *
     * @return
     * @throws SQLException
     * @author Administrator
     */
    public String getRulerViewInfo(String tableName, String lowerName) throws SQLException {
        List<ColumnData> dataList = getColumnDatas(tableName);
        StringBuffer returnStr = new StringBuffer();

        for (ColumnData d : dataList) {
            String name = CommUtil.formatName(d.getColumnName());
            String maxlength = d.getCharmaxLength();
            String isnull = d.getNullable();
            if (!"id".equals(name) && !name.contains("Author") && !name.contains("Time")) {
                returnStr.append(name + ":{").append("\n");
                if ("Y".equals(isnull)) { //如果是可以为空
                    returnStr.append("required:false,").append("\n");
                } else {
                    returnStr.append("required:true,").append("\n");
                }
                if (!"".equals(maxlength) && !"0".equals(maxlength)) {
                    returnStr.append("maxlength:" + maxlength).append("\n");
                } else {
                    returnStr.append("maxlength:10").append("\n"); //默认给10
                }
                returnStr.append("},").append("\n");
            }
        }
        return returnStr.deleteCharAt(returnStr.length() - 1).toString();
    }
}