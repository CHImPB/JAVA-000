package com.hongyuchang.shardingsphere.service;

import com.hongyuchang.shardingsphere.model.mybatis.entity.Order;
import com.hongyuchang.shardingsphere.model.mybatis.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderService
{
    @Autowired
    private OrderMapper orderMapper;

    public Order queryById(long id)
    {
        return orderMapper.selectById(id);
    }
}
