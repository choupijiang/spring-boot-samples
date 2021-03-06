package io.ostenant.springboot.sample.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.apache.bcel.util.ClassLoaderRepository;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.jdbc.datasource.lookup.IsolationLevelDataSourceRouter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@AutoConfigureAfter(DataSourceConfiguration.class)
public class MybatisConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(MybatisConfiguration.class);

    @Resource(name = "masterDataSource")
    private DataSource masterDataSource;

    @Resource(name = "slaveDataSource")
    private DataSource slaveDataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactory(
            @Qualifier("routingDataSourceProxy") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean  sessionFactoryBean = new SqlSessionFactoryBean();
        // 设置数据源
        sessionFactoryBean.setDataSource(dataSource);
        return sessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(
            AbstractRoutingDataSource routingDataSourceProxy) {
        return new DataSourceTransactionManager(routingDataSourceProxy);
    }

    @Bean
    public AbstractRoutingDataSource routingDataSourceProxy() {
        AbstractRoutingDataSource proxy = new ReadWriteSplitRoutingDataSource();

        Map<Object, Object> targetDataResources = new HashMap<>();
        targetDataResources.put(DbContextHolder.DbType.MASTER, masterDataSource);
        targetDataResources.put(DbContextHolder.DbType.SLAVE, slaveDataSource);

        proxy.setDefaultTargetDataSource(masterDataSource); // 默认数据源
        proxy.setTargetDataSources(targetDataResources);
        return proxy;
    }

}
