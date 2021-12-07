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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages =  "org.zzj.dao.mapper.Goods", sqlSessionFactoryRef = "GoodsDBSqlSessionFactory" )
public class GoodsDataSourceConfig {

    @Bean("goodsDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.Goods")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("GoodsDBSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("GoodsDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/goods/*.xml"));
        return bean.getObject();
    }

    @Bean("GoodsSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("GoodsDBSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}