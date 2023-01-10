package ${bussPackage}.dao#if($!entityPackage).${entityPackage}#end;

import java.util.List;
import ${bussPackage}.model#if($!entityPackage).${entityPackage}#end.${className};

/**
 * ClassName: ${className}Mapper <br/>
 * date: 2019年4月21日 下午3:06:04 <br/>
 *
 * @author lvyazhou@jd.com
 */
 
public interface ${className}Mapper{
    
    /**
	 * 批量删除方法
	 * @param ids
	 * @return
	 */
	int deleteByIds(final String[] ids);

	/**
	 * 单个删除方法
	 * @param ids
	 * @return
	 */
	int deleteById(final Integer ids);

	/**
	 * 插入方法
	 * @param record
	 * @return
	 */
    int insert(final ${className} record);
    
	/**
	 * 批量插入方法
	 * @param list
	 * @return
	 */
    int insertBatch(final List<${className}> list);
    
    /**
	 * 根据主键查询实体（查看方法）
	 * @param id
	 * @return
	 */
    ${className} queryById(final Integer id);

    /**
	 * 更新不为空方法
	 * @param record
	 * @return
	 */
    int update${className}BySelective(final ${className} record);

    /**
	 * 查询方法
	 * @param query
	 * @return
	 */
	List<${className}> listByPage(final ${className} query);
    
    /**
	 * 查询方法list
	 * @param query
	 * @return
	 */
    public List<${className}> get${className}MapList(final ${className} query);
}
