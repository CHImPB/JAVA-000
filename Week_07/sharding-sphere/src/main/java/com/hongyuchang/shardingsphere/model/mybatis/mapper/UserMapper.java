package com.hongyuchang.shardingsphere.model.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hongyuchang.shardingsphere.model.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User>
{
}
