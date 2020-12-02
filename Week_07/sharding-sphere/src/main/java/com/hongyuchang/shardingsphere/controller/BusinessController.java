package com.hongyuchang.shardingsphere.controller;

import com.hongyuchang.shardingsphere.model.mybatis.entity.Order;
import com.hongyuchang.shardingsphere.model.mybatis.entity.User;
import com.hongyuchang.shardingsphere.service.OrderService;
import com.hongyuchang.shardingsphere.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessController
{
    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/user/{id}")
    public User queryByUserId(@PathVariable Long id)
    {
        return userService.queryById(id);
    }

    @GetMapping("/order/{id}")
    public Order queryByOrderId(@PathVariable Long id)
    {
        return orderService.queryById(id);
    }
}
