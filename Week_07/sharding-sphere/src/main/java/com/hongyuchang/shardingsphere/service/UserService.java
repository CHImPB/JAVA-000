package com.hongyuchang.shardingsphere.service;

import com.hongyuchang.shardingsphere.model.mybatis.entity.User;
import com.hongyuchang.shardingsphere.model.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    private UserMapper userMapper;

    public User queryById(long id)
    {
        return userMapper.selectById(id);
    }
}
