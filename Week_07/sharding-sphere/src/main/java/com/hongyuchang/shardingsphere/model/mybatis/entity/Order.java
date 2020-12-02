package com.hongyuchang.shardingsphere.model.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("`order`")
public class Order implements Serializable
{
    private Long id;

    private Long userId;

    private String address;
}
