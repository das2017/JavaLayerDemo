package org.zzj.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "org.zzj.dao.mapper.order" , sqlSessionFactoryRef = "orderDBSqlSessionFactory" )
public class OrderDataSourceConfig {

    //默认数据源
    @Primary
    @Bean("orderDataSource")
    //application.yml中配置参数
    @ConfigurationProperties(prefix = "spring.datasource.order")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean("orderDBSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("orderDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //mapper的xml文件位置
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/order/*.xml"));
        return bean.getObject();
    }

    @Primary
    @Bean("orderSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("orderDBSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}