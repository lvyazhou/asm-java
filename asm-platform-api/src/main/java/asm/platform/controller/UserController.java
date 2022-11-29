package asm.platform.controller;

import asm.platform.bean.ResponseEntity;
import asm.platform.dto.user.UserDto;
import asm.platform.exception.BllException;
import asm.platform.exception.NoDataException;
import asm.platform.service.common.JsonSerializeService;
import asm.platform.service.user.UserService;
import asm.platform.vo.user.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

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
public class UserController {

    /**
     * userService
     */
    @Resource
    private UserService userService;

    /**
     * jsonSerializeService
     */
    @Autowired
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
}
