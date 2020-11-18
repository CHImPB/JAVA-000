package com.zhanghongyu.springboot.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zh.hongyu
 */
@Data
@ConfigurationProperties(prefix = "spring.hongyu")
public class HongyuProperties {
    private String teacherName;
    private String weekDate;
    private String studentName;
}
