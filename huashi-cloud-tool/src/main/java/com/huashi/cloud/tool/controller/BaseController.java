package com.huashi.cloud.tool.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author peter
 * @version V1.0 创建时间：17/6/26
 *          Copyright 2017 by PreTang
 */
@RestController
public class BaseController {

    @Value("${pictureDomain}")
    private String from;

    @RequestMapping("/from")
    public String from() {
        return this.from;
    }
}
