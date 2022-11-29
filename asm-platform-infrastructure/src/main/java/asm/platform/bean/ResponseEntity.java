package asm.platform.bean;


import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @param <T>
 * @author lvyz
 */
@ApiModel("返回对象")
@Data
public class ResponseEntity<T> {

    @ApiModelProperty(required = true, value = "响应的状态代码")
    private int code;
    @ApiModelProperty(required = true, value = "响应的消息")
    private String msg;
    @ApiModelProperty(required = true, value = "响应时服务器时间戳")
    private Date timestamp;
    @ApiModelProperty(value = "业务数据")
    private T data;
    @ApiModelProperty(value = "分页数据")
    PageInfo<T> pageInfo;
    @ApiModelProperty(value = "总条数")
    private long iTotalRecords;
    @ApiModelProperty(value = "返回条数")
    private long iTotalDisplayRecords;

    public static <T> ResponseEntity<T> pageInfo(List<T> list) {
        PageInfo<T> pageInfo = new PageInfo<T>(list);
        ResponseEntity<T> entity = success();
        entity.setPageInfo(pageInfo);
        return entity;
    }

    public static <T> ResponseEntity<T> success(T data, long iTotalRecords, long iTotalDisplayRecords) {
        ResponseEntity<T> entity = success();
        entity.setData(data);
        entity.setITotalDisplayRecords(iTotalDisplayRecords);
        entity.setITotalRecords(iTotalRecords);
        return entity;
    }


    public static <T> ResponseEntity<T> success() {
        ResponseEntity<T> entity = new ResponseEntity<T>();
        entity.setCode(com.platform.bean.ResultCode.SUCCESS);
        entity.setMsg(com.platform.bean.ResultMsg.SUCCESS);
        entity.setTimestamp(new Date());
        return entity;
    }

    public static <T> ResponseEntity<T> success(T data) {
        ResponseEntity<T> entity = success();
        entity.setData(data);
        return entity;
    }

    public static <T> ResponseEntity<T> error() {
        ResponseEntity<T> entity = new ResponseEntity<T>();
        entity.setCode(com.platform.bean.ResultCode.ERROR);
        entity.setMsg(com.platform.bean.ResultMsg.ERROR);
        entity.setTimestamp(new Date());
        return entity;
    }

    public static <T> ResponseEntity<T> error(int errorCode) {
        ResponseEntity<T> entity = error();
        entity.setCode(errorCode);
        return entity;
    }

    public static <T> ResponseEntity<T> error(String message) {
        ResponseEntity<T> entity = error();
        entity.setMsg(message);
        return entity;
    }

    public static <T> ResponseEntity<T> successStr(String message) {
        ResponseEntity<T> entity = success();
        entity.setMsg(message);
        return entity;
    }

    public static <T> ResponseEntity<T> error(Integer errorCode, String message) {
        ResponseEntity<T> entity = error();
        entity.setCode(errorCode);
        entity.setMsg(message);
        return entity;
    }
}
