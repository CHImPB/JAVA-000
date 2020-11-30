package com.hongyuchang.multidatabase.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.hongyuchang.multidatabase.mapper.master",
        sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterMybatisConfig {
    /**
     * 注意，此处需要使用MybatisSqlSessionFactoryBean，不是SqlSessionFactoryBean，
     * 否则，使用mybatis-plus的内置函数时就会报invalid bound statement (not found)异常
     */
    @Bean("masterSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("master") DataSource dataSource) throws Exception {
        // 设置数据源
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }
}
