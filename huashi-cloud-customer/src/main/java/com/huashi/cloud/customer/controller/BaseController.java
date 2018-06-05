package com.huashi.cloud.customer.controller;

import org.springframework.web.bind.annotation.RestController;

/**
 * @author peter
 * @version V1.0 创建时间：17/6/26
 *          Copyright 2017 by PreTang
 */
@RestController
public class BaseController {

    /**
     * 获取分页的实体
     * @param params  传入的map集合
     * @return
     */
//    protected PageBean getPageBean(Map<String, Object> params){
//        Integer currentPage = 1,pageSize = 10;
//        if(params.get("page") != null){
//            currentPage = Integer.valueOf(params.get("page").toString()) ;
//        }
//        if(params.get("pageSize") != null){
//            pageSize = Integer.valueOf(params.get("pageSize").toString());
//        }
//
//        PageBean pageBean = new PageBean();
//        pageBean.setCurrentPage(currentPage);
//        pageBean.setPageSize(pageSize);
//        return pageBean;
//    }

}
