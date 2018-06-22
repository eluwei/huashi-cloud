package com.huashi.cloud.customer.view.domain;

import com.huashi.cloud.customer.app.domain.Ad;
import com.huashi.cloud.customer.app.domain.Brand;
import com.huashi.cloud.customer.app.domain.Channel;

import java.util.List;

public class IndexViewDomain {
    //广告实体
    private List<Ad> bannerList;

    //渠道实体
    private List<Channel> channelList;

    //品牌实体
    private List<Brand> brandList;


    public List<Ad> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<Ad> bannerList) {
        this.bannerList = bannerList;
    }

    public List<Channel> getChannelList() {
        return channelList;
    }

    public void setChannelList(List<Channel> channelList) {
        this.channelList = channelList;
    }

    public List<Brand> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<Brand> brandList) {
        this.brandList = brandList;
    }
}
