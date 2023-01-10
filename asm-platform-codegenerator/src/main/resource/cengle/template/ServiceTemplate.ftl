package ${bussPackage}.service#if($!entityPackage).${entityPackage}#end;
import java.util.List;
import ${bussPackage}.model#if($!entityPackage).${entityPackage}#end.${className};
import com.github.pagehelper.PageInfo;
import ${bussPackage}.utils.JsonResponse;

/**
 * ClassName: I${className}Service <br/>
 * date: 2019年4月21日 下午3:01:49 <br/>
 *
 * @author lvyazhou@jd.com
 */
 
public interface I${className}Service{
    
    int delete${className}ByIds(final String[] ids);
	
	int delete${className}ById(final Integer id);

    JsonResponse add${className}(final ${className} record);
        
    ${className} get${className}ById(final Integer id);
            
    int update${className}BySelective(final ${className} record);

    public PageInfo<${className}> listByPage(final ${className} query, final Integer pageNum, final Integer pageSize);
    
    public List<${className}> get${className}MapList(final ${className} query);
}
