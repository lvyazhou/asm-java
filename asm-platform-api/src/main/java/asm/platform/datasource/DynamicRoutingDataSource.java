package asm.platform.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @description 访问数据库时会调用该类的 determineCurrentLookupKey() 方法获取数据库实例的 key
 */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
       // log.info("Current DataSource is [" + DynamicDataSourceContextHolder.getDataSourceKey() + "]");
        return DynamicDataSourceContextHolder.getDataSourceKey();
    }
}
