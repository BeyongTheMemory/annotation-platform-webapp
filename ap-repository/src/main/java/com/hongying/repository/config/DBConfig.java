package com.hongying.repository.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.hongying.repository", annotationClass = Repository.class)
public class DBConfig implements TransactionManagementConfigurer {

    private final static String location = "classpath*:com/hongying/mybatis/*.xml";

    @Bean(name = "dataSource",initMethod = "init", destroyMethod = "close")
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/annotation?characterEncoding=UTF-8&useUnicode=true&connectTimeout=10000&autoReconnect=true&maxReconnects=5&failOverReadOnly=false");
        dataSource.setUsername("root");
        dataSource.setPassword("xugang6671877");
        dataSource.setMaxActive(20);
        try {
            dataSource.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier(value = "dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(location));
        return sqlSessionFactory.getObject();
    }

    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }


}
