package com.huashi.cloud.customer.common.domain.request;



import com.huashi.cloud.customer.common.domain.InventoryProduct;
import com.huashi.cloud.customer.service.AdminService;
import org.springframework.transaction.annotation.Transactional;

/**
 **********************************************
 *  描述：更新库存信息
 *  1、先删除缓存中的数据
 *  2、更新数据库中的数据
 * Simba.Hua
 * 2017年8月30日
 **********************************************
 **/
public class InventoryUpdateDBRequest implements Request{

    private AdminService adminService;
    private InventoryProduct inventoryProduct;

    public InventoryUpdateDBRequest(InventoryProduct inventoryProduct,AdminService adminService){
        this.inventoryProduct = inventoryProduct;
        this.adminService = adminService;
    }

    @Override
    @Transactional
    public Object process() throws Exception {
        return adminService.storeOrupdateChannel(null,null);
    }

    @Override
    public Integer getProductId() {
        // TODO Auto-generated method stub
        return inventoryProduct.getProductId();
    }
    @Override
    public boolean isForceFefresh() {
        // TODO Auto-generated method stub
        return false;
    }

}