package ${bussPackage}.service.impl#if($!entityPackage).${entityPackage}#end;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${bussPackage}.dao#if($!entityPackage).${entityPackage}#end.${className}Mapper;
import ${bussPackage}.service#if($!entityPackage).${entityPackage}#end.I${className}Service;
import ${bussPackage}.model#if($!entityPackage).${entityPackage}#end.${className};
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;
import ${bussPackage}.utils.JsonResponse;

/**
 * <b>功能：</b>${className}Service<br>
 */
 
@Service("$!{lowerName}Service")
public class  ${className}ServiceImpl implements I${className}Service {

	private final static Logger log = LoggerFactory.getLogger(${className}ServiceImpl.class);
	
	@Autowired
    private ${className}Mapper ${lowerName}Mapper;
	public ${className}Mapper get${className}Mapper() {
		return ${lowerName}Mapper;
	}
	
	@Override
	public int delete${className}ByIds(final String[] ids){
		return this.${lowerName}Mapper.deleteByIds(ids);
	}
	
	@Override
	public int delete${className}ById(final Integer id){
		return this.${lowerName}Mapper.deleteById(id);
	}
	
	@Override
    public JsonResponse add${className}(final ${className} record){
	    	JsonResponse res;
	    	this.${lowerName}Mapper.insert(record);
	    	res = new JsonResponse();
	    	return res;
    }
    
	@Override
    public ${className} get${className}ById(final Integer id){
    		return this.${lowerName}Mapper.queryById(id);
    }
	
	@Override
	public int update${className}BySelective(final ${className} record){
		return this.${lowerName}Mapper.update${className}BySelective(record);
    }
    
	@Override
	public PageInfo<${className}> listByPage(final ${className} query, final Integer pageNum,final Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		PageInfo<${className}> pageInfo = new PageInfo<${className}>(this.${lowerName}Mapper.listByPage(query));
		return pageInfo;
	}
	
	@Override
    public List<${className}> get${className}MapList(final ${className} query){
		return this.${lowerName}Mapper.get${className}MapList(query);
	}
}
