package com.huashi.cloud.customer.controller.admin;


import com.huashi.cloud.common.page.PageBean;
import com.huashi.cloud.common.result.ResultData;
import com.huashi.cloud.customer.app.domain.Channel;
import com.huashi.cloud.customer.controller.BaseController;
import com.huashi.cloud.customer.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.File;

@RestController
@RequestMapping("/admin")
public class AdminBaseController extends BaseController {

    @Autowired
    AdminService adminService;

    /**
     * 客户微信小程序登录接口
     */
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public Object userLogin(@NotNull String userName, @NotNull String password, HttpServletRequest request) throws Exception{
        return ResultData.DATA(adminService.userLogin(userName, password, request.getHeader("HTTP_X_FORWARDED_FOR")));
    }

    /**
     * 管理后台 获取首页分类列表
     * @param name
     * @param page
     * @param pageSize
     */
    @RequestMapping(value = "/channel/getChannelList", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public Object getChannelList(String name, Integer page, Integer pageSize) throws Exception{
        PageBean pageBean = getPageBean(page, pageSize);
        return ResultData.DATA(adminService.listChannel(name, pageBean));
    }

    /**
     * 管理后台 获取首页分类详情
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/channel/getChannelInfo", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public Object getChannelInfo(@NotNull Integer id) throws Exception{
        return ResultData.DATA(adminService.getChannelInfo(id));
    }

    /**
     * 管理后台 保存、更新 首页分类
     */
    @RequestMapping(value = "/channel/store", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public Object storeChannel(@NotNull Channel channel,  MultipartFile file) throws Exception{
        return ResultData.DATA(adminService.storeOrupdateChannel(channel, file));
    }

    /**
     * 管理后台 （删除）更新 首页分类
     */
    @RequestMapping(value = "/channel/onlineChannelById", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public Object onlineChannelById(@NotNull Integer id, @NotNull Integer online) throws Exception{
        return ResultData.DATA(adminService.onlineChannelById(id, online));
    }

    /**
     * 管理后台 获取商品列表
     */
    @RequestMapping(value = "/goods/goodsList", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public Object getGoodList(String name, Integer page, Integer pageSize) throws Exception{
        PageBean pageBean = getPageBean(page, pageSize);
        return ResultData.DATA(adminService.getGoodsList(name, pageBean));
    }


    /**
     * 管理后台 根据id获取商品详情
     */
    @RequestMapping(value = "/goods/getAllCategory", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public Object getAllCategory() throws Exception{
        return ResultData.DATA(adminService.getAllCategory());
    }

    /**
     * 管理后台 根据id获取商品详情
     */
    @RequestMapping(value = "/goods/getGoodsInfo", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public Object getGoodsInfo(@NotNull Integer id) throws Exception{
        return ResultData.DATA(adminService.getGoodsInfo(id));
    }



}
