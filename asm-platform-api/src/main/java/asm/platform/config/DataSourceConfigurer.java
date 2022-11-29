package asm.platform.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import asm.platform.bean.DataSourceKey;
import asm.platform.bean.DataSourceType;
import asm.platform.datasource.DynamicDataSourceContextHolder;
import asm.platform.datasource.DynamicRoutingDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lvyz
 * @description 数据源配置类，在该类中生成多个数据源实例并将其注入到 ApplicationContext 中
 */
@Configuration
@MapperScan(basePackages = {"asm.platform.mapper.**"})
public class DataSourceConfigurer {

    private final String MAPPER_LOCATION = "classpath*:mapper/**/*.xml";


    @Bean(DataSourceType.MASTER)
    @ConfigurationProperties(prefix = "datasource.master")
    public DataSource master() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(DataSourceType.SALVE)
    @ConfigurationProperties(prefix = "datasource.slave")
    public DataSource salve() {
        return DruidDataSourceBuilder.create().build();
    }
    // ----------------------------------------------------------------------

    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<Object, Object>(DataSourceKey.values().length);
        dataSourceMap.put(DataSourceKey.MASTER.name(), master());
        dataSourceMap.put(DataSourceKey.SALVE.name(), salve());

        // 将 driverOrderRecordMaster 数据源作为默认指定的数据源
        dynamicRoutingDataSource.setDefaultTargetDataSource(master());
        // 指定使用到的所有数据源
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);

        // 将数据源的 key 放到数据源上下文的 key 集合中，用于切换时判断数据源是否有效
        DynamicDataSourceContextHolder.dataSourceKeys.addAll(dataSourceMap.keySet());

        return dynamicRoutingDataSource;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MAPPER_LOCATION));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean("sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /***
     * 注入 DataSourceTransactionManager 事务
     */
    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("dynamicDataSource") DataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }
}
