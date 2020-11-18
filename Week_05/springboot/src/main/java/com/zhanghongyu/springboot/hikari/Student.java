package com.zhanghongyu.springboot.hikari;

import lombok.Data;

import javax.naming.ldap.PagedResultsControl;

/**
 * @author zh.hongyu
 */
@Data
public class Student {
    private Integer id;
    private String name;
    private Integer age;
}
