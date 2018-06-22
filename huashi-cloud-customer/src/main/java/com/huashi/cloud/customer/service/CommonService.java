package com.huashi.cloud.customer.service;


import com.huashi.cloud.customer.app.domain.Ad;
import com.huashi.cloud.customer.app.domain.Brand;
import com.huashi.cloud.customer.app.domain.Channel;
import com.huashi.cloud.customer.repository.CloudAppExtendsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class CommonService {

    @Autowired
    protected CloudAppExtendsRepository cloudAppExtendsRepository;

    /**
     * 查询 小程序首页banner
     * @return
     */
    public List<Ad> listBanner() {
        return cloudAppExtendsRepository.findAll(Ad.class, null, new Object[]{}, null, 0, 0);
    }

    /**
     * 查询 小程序首页渠道
     * @return
     */
    public List<Channel> listChannel() {
        return cloudAppExtendsRepository.findAll(Channel.class, "online = 1", new Object[]{}, null, 0, 0);
    }

    /**
     * 查询 小程序首页品牌列表
     * @return
     */
    public List<Brand> listBrand() {
        return cloudAppExtendsRepository.findAll(Brand.class, null, new Object[]{}, null, 0, 0);
    }






}