package asm.platform.common.enums;


/**
 * ClassName: ErrorCodeEnum <br/>
 * date: 2022年11月18日 上午11:11:11 <br/>
 * 服务执行结果
 * @author lvyazhou@qq.cn
 */

public enum ErrorCodeEnum {
    SUCCESS(0, "success"),

    VERIFYCODE_ERROR(100, "验证码校验失败，请重新录入!"),
    XSS_ERROR(200, "录入的内容含有非法字符，请重新录入!"),
    TIMEOUT_ERROR(300, "用户请求超时，请重新登录!"),
    NEEDLOGIN_ERROR(400, "需要重新登录"),
    UNKNOWN_ERROR(900, "unknown error"),
    FILETYPE_ERROR(903, "上传的文件类型不匹配，请重新上传！"),
    FILESIZE_ERROR(904, "文件大小不得大于5M！"),
    FILE_DATA_ERROR(905, "文件不能为空！"),
    FILE_DATA_ERROR_1(906, "上传文件失败！"),
    VALID_MAP_ERROR(101, "传入对象不能为空！"),
    VALID_PROJECT_MEMBER_ERROR(102, "项目成员不能为空！"),
    VALID_PROJECT_DETAIL_ERROR(103, "项目详情信息不存在！"),
    VALID_DW_FILE_ERROR(104, "下载报告不存在！"),
    VALID_DW_URL_ERROR(105, "下载报告链接不存在！"),
    VALID_PRODATE_ERROR(106, "项目日期不能为空！"),
    VALID_PROJECT_NAME_ERROR(107, "项目名称不能为空！"),
    VALID_PROJECT_OPTYPE_ERROR(108, "操作类型不能为空！"),
    VALID_REPT_REPORT_ERROR(109, "报告-项目信息不能为空！"),
    VALID_REPT_SEC_LEAK_ERROR(110, "报告-安全概述漏洞类型不能为空！"),
    VALID_REPT_SEC_COUNT_ERROR(111, "报告-安全概述漏洞统计不能为空！"),
    VALID_REPT_LEAK_DETAIL_ERROR(112, "报告-漏洞明细不能为空！"),
    VALID_REPT_GENERATE_ERROR(113, "生成报告URL为空！"),
    VALID_REPT_GENERATE_ERROR_1(114, "生成报告失败！"),
    VALID_USER_ERROR_1(115, "用户SID或REF链接不能为空！"),
    VALID_USER_ERROR_2(116, "ldap获取用户信息失败！"),
    VALID_USER_ERROR_3(117, "ldap转换对象失败！"),
    VALID_USER_ERROR_4(118, "非法用户登录，请联系管理员！"),
    VALID_RANSOMWARE_ERROR(119, "无返回勒索识别日志信息！"),
    VALID_RANSOMWARE_DW_ERROR(120, "勒索识别日志信息下载失败！"),
    VALID_RANSOMWARE_ADD_ERROR_1(121, "勒索识别加密文件不能为空！"),
    VALID_RANSOMWARE_ADD_ERROR_2(122, "勒索信和通信信箱不能全部为空！"),
    VALID_KEYGEN_ERROR_1(123, "获取序列号为空，请联系管理员！"),
    VALID_SYS_USER_ERROR_1(124, "用户leader不能为空！"),
    VALID_SYS_USER_ERROR_2(125, "工具列表不能为空！"),
    VALID_SYS_USER_ERROR_3(126, "该用户已经存在！"),
    VALID_SYS_USER_ERROR_4(127, "该用户不存在！"),
    VALID_TOOL_ERROR_1(128, "工具不存在，请重新选择！"),
    VALID_TOOL_ERROR_2(129, "用户Leader不存在,请先设置Leader！"),
    VALID_TOOL_ERROR_3(130, "工单状态驳回，驳回理由不能为空！"),
    VALID_TOOL_ERROR_4(131, "找不到工单申请信息！"),
    VALID_TOOL_ERROR_5(132, "只有工单状态为待审批，才可以通过/驳回！"),
    VALID_TOOL_ERROR_6(133, "未查询到驳回原因！"),
    VALID_TOOL_ERROR_7(134, "只有工单状态为已驳回，才能查看驳回原因！"),
    VALID_TOOL_ERROR_8(135, "只有工单状态为已审核，才能下载工具！"),
    VALID_TOOL_ERROR_9(136, "只有工单状态为已审核，才能获取序列号！"),
    VALID_TOOL_ERROR_10(137, "只有用户leader才能审批/驳回！"),
    VALID_TOOL_ERROR_11(138, "只有用户leader才能获取license！"),
    VALID_TOOL_ERROR_12(139, "工具有正在上传的文件，请稍后上传！");

    private int code;
    private String errorMessage;

    ErrorCodeEnum(int code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
