package asm.platform.controller;

import asm.platform.bean.ResponseEntity;
import asm.platform.dto.login.LoginDto;
import asm.platform.exception.NoDataException;
import asm.platform.service.common.JsonSerializeService;
import asm.platform.service.login.LoginService;
import asm.platform.vo.login.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 用户登录 Controller
 *
 * @author lvyazhou@qq.com
 * @date 2022-11-11
 */
@Api(tags = "用户登录")
@Slf4j
@RestController
public class LoginController {

    /**
     * loginService
     */
    @Resource
    private LoginService loginService;

    /**
     * jsonSerializeService
     */
    @Autowired
    private JsonSerializeService jsonSerializeService;

    @ApiOperation(value = "登录")
    @PostMapping(value = "/login")
    public ResponseEntity<LoginVo> login(@RequestBody @Valid LoginDto reqDto) {
        log.info("[Request][/login] request param: {}", jsonSerializeService.serialize(reqDto));

        try {
            LoginVo loginVo = this.loginService.login(reqDto);
            return ResponseEntity.success(loginVo);
        } catch (NoDataException e) {
            log.error("[Request][/login] response error : ", ExceptionUtils.getStackTrace(e));
            return ResponseEntity.error(e.getrCode(),e.getMessage());
        }
    }

    @ApiOperation(value = "test", notes = "", httpMethod = "GET")
    @GetMapping("/test")
    public String demo(){
        log.info("test controller");
        return "demo";
    }
}
