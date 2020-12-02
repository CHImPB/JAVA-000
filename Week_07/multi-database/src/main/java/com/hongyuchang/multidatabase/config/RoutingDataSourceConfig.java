package com.hongyuchang.multidatabase.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @author Administrator
 */
public class RoutingDataSourceConfig extends AbstractRoutingDataSource {
    private static final ThreadLocal<String> threadLocalDataSourceKey = new ThreadLocal<>();

    public RoutingDataSourceConfig(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }
    @Override
    public DataSource determineTargetDataSource(){
        return super.determineTargetDataSource();
    }
    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSource();
    }

    public static void setDataSource(String dataSource) {
        threadLocalDataSourceKey.set(dataSource);
    }

    public static String getDataSource() {
        return threadLocalDataSourceKey.get();
    }

    public static void clearDataSource() {
        threadLocalDataSourceKey.remove();
    }
}
