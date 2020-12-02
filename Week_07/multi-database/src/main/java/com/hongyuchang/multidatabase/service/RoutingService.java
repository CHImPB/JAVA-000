package com.hongyuchang.multidatabase.service;

import com.hongyuchang.multidatabase.annotation.RoutingWith;
import com.hongyuchang.multidatabase.config.RoutingDataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoutingService {
    @Autowired
    RoutingDataSourceConfig routingDataSourceConfig;

    private JdbcTemplate getJdbcTemplate(){
        return new JdbcTemplate(routingDataSourceConfig);
    }

    @RoutingWith(readOnly = true)
    public List<Map<String,Object>> getTop10Order()
    {
        String sql="select * from tb_order limit 0,10";
        final JdbcTemplate jdbcTemplate = getJdbcTemplate();
        return jdbcTemplate.queryForList(sql);
    }
}
