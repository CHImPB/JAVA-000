package com.hongyuchang.multidatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Administrator
 */
@Service
public class OrderService {
    @Autowired
    @Qualifier("masterJdbcTemplate")
    JdbcTemplate masterJdbcTemplate;

    @Autowired
    @Qualifier("slaveJdbcTemplate")
    JdbcTemplate slaveJdbcTemplate;

    public List<Map<String,Object>> getTop10Order()
    {
        String sql="select * from tb_order limit 0,10";
        return slaveJdbcTemplate.queryForList(sql);
    }

    public void addOrder()
    {
        String sql ="insert into tb_order(id,order_code,order_time,address,total_amount,user_id)values(?,?,now(),'~',?,?)";
        Object[] params = new Object[]{1000001, UUID.randomUUID().toString(),1,666};
        masterJdbcTemplate.update(sql,params);
    }
}
