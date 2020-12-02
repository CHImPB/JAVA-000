package com.hongyuchang.multidatabase.config;

import com.hongyuchang.multidatabase.constant.DataSourceConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
@Configuration
@PropertySource("classpath:config/jdbc.properties")
public class DatasourceConfig {
    @Bean("master")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean("masterJdbcTemplate")
    public JdbcTemplate masterJdbcTemplate() {
        return new JdbcTemplate(masterDataSource());
    }

    @Bean("slave")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean("slaveJdbcTemplate")
    public JdbcTemplate slaveJdbcTemplate() {
        return new JdbcTemplate(slaveDataSource());
    }

    @Bean
    public RoutingDataSourceConfig getRoutingDataSourceConfig(){
        Map<Object, Object> targetDataSources = new HashMap<>(5);
        targetDataSources.put(DataSourceConstant.MASTER, masterDataSource());
        targetDataSources.put(DataSourceConstant.SLAVE, slaveDataSource());
        return new RoutingDataSourceConfig(slaveDataSource(), targetDataSources);
    }
}
