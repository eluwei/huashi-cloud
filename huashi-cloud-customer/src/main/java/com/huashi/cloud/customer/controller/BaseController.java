package com.huashi.cloud.customer.controller;

import com.huashi.cloud.common.page.PageBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author peter
 * @version V1.0 创建时间：17/6/26
 *          Copyright 2017 by PreTang
 */
@RefreshScope
@RestController
public class BaseController {

    /**
     * 获取分页的实体
     * @param page  传入的map集合
     * @return
     */
    protected PageBean getPageBean(Integer page, Integer pageSize){
        if(page == null){
            page = 1 ;
        }
        if(pageSize == null){
            pageSize = 10;
        }
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(page);
        pageBean.setPageSize(pageSize);
        return pageBean;
    }

//    @Value("${pictureDomain}")
//    private String from;
//
//    @RequestMapping("/from")
//    public String from() {
//        return this.from;
//    }
}
