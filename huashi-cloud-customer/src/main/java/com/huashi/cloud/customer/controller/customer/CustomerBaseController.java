package com.huashi.cloud.customer.controller.customer;

import com.huashi.cloud.common.result.ResultData;
import com.huashi.cloud.customer.controller.BaseController;
import com.huashi.cloud.customer.service.CustomerService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 客户相关联服务
 * User: Administrator
 * Date: 2017/11/1
 * Time: 13:41
 */

@RestController
@RequestMapping("/customer/base")
public class CustomerBaseController extends BaseController{

    private Log log = LogFactory.getLog(CustomerBaseController.class);

    @Autowired
    CustomerService customerService;

    /**
     * 获取某个城市某个类型的广告
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public Object listAdvertByType(@RequestParam Map<String, Object> params){
        return ResultData.DATA(customerService.listCustomer());
    }
}
