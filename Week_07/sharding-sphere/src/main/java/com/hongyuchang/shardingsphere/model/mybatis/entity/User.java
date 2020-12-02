package com.hongyuchang.shardingsphere.model.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName("`user`")
public class User implements Serializable
{
    private Long id;
    
    private String userName;
}
