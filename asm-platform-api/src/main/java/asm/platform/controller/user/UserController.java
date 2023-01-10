package asm.platform.controller.user;

import asm.platform.bean.ResponseEntity;
import asm.platform.controller.BaseController;
import asm.platform.dto.user.UserDto;
import asm.platform.dto.user.UserQueryDto;
import asm.platform.exception.BllException;
import asm.platform.exception.NoDataException;
import asm.platform.service.common.JsonSerializeService;
import asm.platform.service.user.UserService;
import asm.platform.vo.user.UserVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 用户管理 Controller
 *
 * @author lvyazhou@qq.com
 * @date 2022-11-11
 */
@Api(tags = "用户管理")
@Slf4j
@RestController
@RequestMapping(value = "user")
public class UserController extends BaseController {

    /**
     * userService
     */
    @Resource
    private UserService userService;

    /**
     * jsonSerializeService
     */
    @Resource
    private JsonSerializeService jsonSerializeService;

    @ApiOperation(value = "用户添加")
    @PostMapping(value = "/save")
    public ResponseEntity save(@RequestBody @Valid UserDto reqDto) {
        log.info("[Request][/user/save] request param: {}", jsonSerializeService.serialize(reqDto));

        try {
            this.userService.add(reqDto);
            return ResponseEntity.success();
        } catch (BllException e) {
            log.error("[Request][/user/save] response error : ", ExceptionUtils.getStackTrace(e));
            return ResponseEntity.error(e.getrCode(), e.getMessage());
        }
    }

    @ApiOperation(value = "用户列表")
    @GetMapping(value = "/list")
    public ResponseEntity<List<UserVo>> findUserList(
            @ApiParam(name="用户信息",required = false) @RequestBody(required = false) UserQueryDto userQueryDto,
            @ApiParam(name="起始页",value="pageIndex",defaultValue = "1",required = false) @RequestParam(defaultValue = "1",required = false)  Integer pageIndex,
            @ApiParam(name="每页大小",value="pageSize",defaultValue = "10",required = false) @RequestParam(defaultValue = "20",required = false)  Integer pageSize
            )
    {
        log.info("[Request][/user/list] request param: {}", jsonSerializeService.serialize(userQueryDto));
        startPage();
        PageInfo<UserVo> pageInfo = new PageInfo<>(this.userService.findUserList(userQueryDto));
        return ResponseEntity.success(pageInfo.getList(),pageInfo.getTotal(),pageInfo.getSize());
    }
}
