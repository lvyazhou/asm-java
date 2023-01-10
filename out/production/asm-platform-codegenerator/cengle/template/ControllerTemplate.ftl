package ${bussPackage}#if($!controllerEntityPackage).${controllerEntityPackage}#end#if($!entityPackage).${entityPackage}#end;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import ${bussPackage}.model#if($!entityPackage).${entityPackage}#end.${className};
import ${bussPackage}.service#if($!entityPackage).${entityPackage}#end.I${className}Service;
import com.jd.sec.service.ISaOperlogService;

import ${bussPackage}.enums.ErrorCodeEnum;
import ${bussPackage}.utils.JsonResponse;
import ${bussPackage}.utils.ListResVo;
import ${bussPackage}.utils.ValidUtil;

import com.jd.sec.vo.SaOperlog;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * ClassName: ${className}Controller <br/>
 * date: 2019年11月11日 上午11:11:11 <br/>
 * @author lvyazhou@jd.com
 */
@RequestMapping("${lowerName}")
@ResponseBody
@RestController
@Slf4j
public class ${className}Controller{

	private final static logger log = loggerFactory.getlogger(${className}Controller.class);
	
	@Autowired
	private I${className}Service ${lowerName}Service;
	@Autowired
    private ISaOperlogService operlogService;
	
	/**
	 * get${className}MapList:(${tableNameSimple}-列表). <br/>
	 * @author lvyazhou@jd.com
	 * @param query
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "${lowerName}MapList",method = RequestMethod.GET)
	public JsonResponse<ListResVo> get${className}MapList(
			final ${className} query,final HttpServletRequest request) {
		log.info("查询${className}Map列表");
		JsonResponse res = null;
		
		try {
			final List<${className}> ${lowerName}List = ${lowerName}Service.get${className}MapList(query);
			res = new JsonResponse<ListResVo>(new ListResVo(${lowerName}List.size(),${lowerName}List));
		} catch(Exception ce){
			log.error("${className}全部列表查询异常: {}", ExceptionUtils.getStackTrace(ce));
			res = new JsonResponse(ErrorCodeEnum.UNKNOWN_ERROR.getCode(),ErrorCodeEnum.UNKNOWN_ERROR.getErrorMessage());
		} finally {
			log.info("${lowerName}Service {} : {}",JSON.toJSONString(query),JSON.toJSONString(res));
			operLogService.addOperLog(
                    SaOperLog.builder().opErp(LoginContext.getLoginContext().getPin()).apiUrl(request.getRequestURI()).inputArgs(JSON.toJSONString(query)).opResouce("${lowerName}").opType(4).operLogResult("success").build()
            );
		}
		return res;
	}
	
	/**
	 * listByPage:(${tableNameSimple}-查询分页). <br/>
	 * @author lvyazhou@jd.com
	 * @param query
	 * @param pageNum
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "list",method = RequestMethod.GET)
	public  JsonResponse<ListResVo> listByPage(
			final ${className} query,@RequestParam(defaultValue="1",value="pageNum") Integer pageNum,@RequestParam(defaultValue="10",value="pageSize") Integer pageSize, HttpServletRequest request){
		log.info("查询${className}分页列表");
		JsonResponse res = null;
		
		try {
			final PageInfo<${className}> ${lowerName}List = ${lowerName}Service.listByPage(query,pageNum,pageSize);
			res = new JsonResponse<ListResVo>(new ListResVo(${lowerName}List.getTotal(),${lowerName}List.getList()));
		} catch(Exception ce){
			log.error("${className}分页查询异常: {}", ExceptionUtils.getStackTrace(ce));
			res = new JsonResponse(ErrorCodeEnum.UNKNOWN_ERROR.getCode(),ErrorCodeEnum.UNKNOWN_ERROR.getErrorMessage());
		} finally {
			log.info("${lowerName}List {} : {}",JSON.toJSONString(query),JSON.toJSONString(res));
			operLogService.addOperLog(
                    SaOperLog.builder().opErp(LoginContext.getLoginContext().getPin()).apiUrl(request.getRequestURI()).inputArgs(JSON.toJSONString(query)).opResouce("${lowerName}").opType(4).operLogResult("success").build()
            );
		}
		return res;
	}
	
	/**
	 * add:(${tableNameSimple}-增加方法). <br/>
	 * @author lvyazhou@jd.com
	 * @param ${lowerName}
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public  JsonResponse add(
			final @Validated @RequestBody ${className} ${lowerName},final BindingResult result, HttpServletRequest request)
	{
		log.info("${className}增加操作");
		JsonResponse res = null;
		
		try {
			final String validRes = ValidUtil.getErrMsg(result);
			if (validRes != null) {
				res = new JsonResponse(validRes);
			} else {
				res = this.${lowerName}Service.add${className}(${lowerName});
			}
		} catch(Exception ce){
			log.error("${className}增加操作异常: {}", ExceptionUtils.getStackTrace(ce));
			res = new JsonResponse(ErrorCodeEnum.UNKNOWN_ERROR.getCode(),ErrorCodeEnum.UNKNOWN_ERROR.getErrorMessage());
		} finally {
			log.info("${lowerName}ServiceAdd {} : {}",JSON.toJSONString(${lowerName}),JSON.toJSONString(res));
			operLogService.addOperLog(
                    SaOperLog.builder().opErp(LoginContext.getLoginContext().getPin()).apiUrl(request.getRequestURI()).inputArgs(JSON.toJSONString(${lowerName})).opResouce("${lowerName}").opType(1).operLogResult(JSON.toJSONString(res)).build()
            );
		}
		return res;
	}
	
	/**
	 * update:(修改${tableNameSimple}方法). <br/>
	 * @author lvyazhou@jd.com
	 * @return
	 */
	@RequestMapping(value = "edit", method = RequestMethod.PUT)
	public JsonResponse update(
			final @Validated @RequestBody ${className} ${lowerName},final BindingResult result, HttpServletRequest request)
	{
		log.info("${className}修改操作");
		JsonResponse res = null;
		
		try {
			final String validRes = ValidUtil.getErrMsg(result);
			if (validRes != null) {
				res = new JsonResponse(validRes);
			} else {
				this.${lowerName}Service.update${className}BySelective(${lowerName});
				res = new JsonResponse();
			}
		} catch(Exception ce){
			log.error("${className}修改操作异常: {}", ExceptionUtils.getStackTrace(ce));
			res = new JsonResponse(ErrorCodeEnum.UNKNOWN_ERROR.getCode(),ErrorCodeEnum.UNKNOWN_ERROR.getErrorMessage());
		} finally {
			log.info("${lowerName}ServiceEdit {} : {}",JSON.toJSONString(${lowerName}),JSON.toJSONString(res));
			operLogService.addOperLog(
                    SaOperLog.builder().opErp(LoginContext.getLoginContext().getPin()).apiUrl(request.getRequestURI()).inputArgs(JSON.toJSONString(${lowerName})).opResouce("${lowerName}").opType(3).operLogResult(JSON.toJSONString(res)).build()
            );
		}
		return res;
	}
	
	/**
	 * detail:(查看${tableNameSimple}方法). <br/>
	 * @author lvyazhou@jd.com
	 * @param id
	 * @return
	 */
	@GetMapping(value="detail")
	public JsonResponse detail(final Integer id, HttpServletRequest request)
	{
		log.info("${className}查看操作: {}", ExceptionUtils.getStackTrace(ce));
		JsonResponse res = null;
		
		try {
			res = new JsonResponse(this.${lowerName}Service.get${className}ById(id));
		}catch(Exception ce){
			log.error("${className}查看操作异常: {}", ExceptionUtils.getStackTrace(ce));
			res = new JsonResponse(ErrorCodeEnum.UNKNOWN_ERROR.getCode(),ErrorCodeEnum.UNKNOWN_ERROR.getErrorMessage());
		}finally {
			log.info("${lowerName}ServiceDetail {} : {}",JSON.toJSONString(id),JSON.toJSONString(res));
			operLogService.addOperLog(
                    SaOperLog.builder().opErp(LoginContext.getLoginContext().getPin()).apiUrl(request.getRequestURI()).inputArgs(id.toString()).opResouce("${lowerName}").opType(2).operLogResult("success").build()
            );
		}
		return res;
	}
	
	/**
	 * @description 批量删除${tableNameSimple}
	 * @param id
	 * @return
	 */	
    @DeleteMapping(value="batchremove")
	public JsonResponse batchRemove(final String[] id, HttpServletRequest request){
		log.info("${className}批量删除操作");
		JsonResponse res = null;
		
		try {
			if(id != null && id.length>0){
				this.${lowerName}Service.delete${className}ByIds(id);
				res = new JsonResponse();
			}
		}catch(Exception ce){
			log.error("${className}批量删除异常: {}", ExceptionUtils.getStackTrace(ce));
			res = new JsonResponse(ErrorCodeEnum.UNKNOWN_ERROR.getCode(),ErrorCodeEnum.UNKNOWN_ERROR.getErrorMessage());
		} finally {
			log.info("${lowerName}ServiceBatchRemove {} : {}",JSON.toJSONString(id),JSON.toJSONString(res));
			operLogService.addOperLog(
                    SaOperLog.builder().opErp(LoginContext.getLoginContext().getPin()).apiUrl(request.getRequestURI()).inputArgs(JSON.toJSONString(id)).opResouce("${lowerName}").opType(10).operLogResult(JSON.toJSONString(res)).build()
            );
		}
		return res;
	}
	
	/**
	 * remove:(单个删除${tableNameSimple}). <br/>
	 * @author lvyazhou@jd.com
	 * @param id
	 * @return
	 */
	@DeleteMapping(value="remove")
	public JsonResponse remove(final Integer id, HttpServletRequest request){
		log.info("${className}删除操作");
		JsonResponse res = null;
		
		try {
			this.${lowerName}Service.delete${className}ById(id);
			res = new JsonResponse();
		}catch(Exception ce){
			log.error("${className}单个删除异常: {}", ExceptionUtils.getStackTrace(ce));
			res = new JsonResponse(ErrorCodeEnum.UNKNOWN_ERROR.getCode(),ErrorCodeEnum.UNKNOWN_ERROR.getErrorMessage());
		}finally {
			log.info("${lowerName}ServiceDelete {} : {}",JSON.toJSONString(id),JSON.toJSONString(res));
			operLogService.addOperLog(
                    SaOperLog.builder().opErp(LoginContext.getLoginContext().getPin()).apiUrl(request.getRequestURI()).inputArgs(JSON.toJSONString(id)).opResouce("${lowerName}").opType(10).operLogResult(JSON.toJSONString(res)).build()
            );
		}
		return res;
	}
}
