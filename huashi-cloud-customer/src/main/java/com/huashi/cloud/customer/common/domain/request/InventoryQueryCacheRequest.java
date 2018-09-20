package com.huashi.cloud.customer.common.domain.request;

import com.huashi.cloud.customer.service.AdminService;

/**
 **********************************************
 *  描述：查询缓存数据
 *  1、从数据库中查询
 *  2、从数据库中查询后插入到缓存中
 * Simba.Hua
 * 2017年8月30日
 **********************************************
 **/
public class InventoryQueryCacheRequest implements Request {
    private AdminService adminService;
    private Integer productId;
    private boolean isForceFefresh;

    public InventoryQueryCacheRequest(Integer productId,AdminService adminService,boolean isForceFefresh) {
        this.productId = productId;
        this.adminService = adminService;
        this.isForceFefresh = isForceFefresh;
    }
    @Override
    public Object process() {
        adminService.getAllCategory();
        return false;
    }

    @Override
    public Integer getProductId() {
        // TODO Auto-generated method stub
        return productId;
    }

    @Override
    public boolean isForceFefresh() {
        return isForceFefresh;
    }

    public void setForceFefresh(boolean isForceFefresh) {
        this.isForceFefresh = isForceFefresh;
    }


}