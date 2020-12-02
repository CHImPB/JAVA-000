package com.hongyuchang.shardingsphere.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController
{
    @GetMapping("/hello/{userName}")
    public String helloWorld(@PathVariable String userName)
    {
        return "Hello:" + userName;
    }
}
