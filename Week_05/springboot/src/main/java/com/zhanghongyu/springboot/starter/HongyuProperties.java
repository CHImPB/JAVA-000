package com.zhanghongyu.springboot.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.hongyuconfigration")
public class HongyuProperties {
    private String teacherName;
    private String week05Date;
    private String studentName;
}
