package com.huashi.cloud.customer.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.huashi.cloud.common.exception.BusinessException;
import com.huashi.cloud.common.exception.result.BusinessExceptionResult;
import com.huashi.cloud.common.result.ResultData;
import com.huashi.cloud.common.result.ResultState;
import com.huashi.cloud.common.utils.WeixinLoginUtils;
import com.huashi.cloud.customer.controller.BaseController;
import com.huashi.cloud.customer.app.domain.Ad;
import com.huashi.cloud.customer.app.domain.Brand;
import com.huashi.cloud.customer.app.domain.Channel;
import com.huashi.cloud.customer.service.CommonService;
import com.huashi.cloud.customer.view.domain.IndexViewDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 客户相关联服务
 * User: Administrator
 * Date: 2017/11/1
 * Time: 13:41
 */

@RestController
@RequestMapping("/app")
public class AppBaseController extends BaseController{

    @Autowired
    CommonService commonService;

    /**
     * 客户微信小程序登录接口
     */
    @RequestMapping(value = "/loginByWeixin", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public Object loginByWeixin(@NotNull String code, @NotNull String userInfo){
        JSONObject weixinUserInfo = WeixinLoginUtils.getWeixinUserInfo(code, userInfo);
        if(weixinUserInfo == null) throw new BusinessException("微信登录异常!", new BusinessExceptionResult(ResultState.BUSINESS_ERROR.name(), "微信登录异常!"));
        return ResultData.SUCCESS();
    }

    /**
     * 获取某个城市某个类型的广告
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public Object listAdvertByType(){
        //获取小程序首页的banner
        List<Ad> bannerList = commonService.listBanner();
        List<Channel> channelList = commonService.listChannel();
        List<Brand> brandList = commonService.listBrand();
        IndexViewDomain indexViewDomain = new IndexViewDomain();
        indexViewDomain.setBannerList(bannerList);
        indexViewDomain.setChannelList(channelList);
        indexViewDomain.setBrandList(brandList);
        return ResultData.DATA(indexViewDomain);
    }







}
