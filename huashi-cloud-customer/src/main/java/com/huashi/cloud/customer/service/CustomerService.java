package com.huashi.cloud.customer.service;


import com.huashi.cloud.common.page.PageBean;
import com.huashi.cloud.customer.domain.Ad;
import com.huashi.cloud.customer.domain.Brand;
import com.huashi.cloud.customer.domain.Channel;
import com.huashi.cloud.customer.domain.Customer;
import com.huashi.cloud.customer.repository.CustomerExtendsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class CustomerService {

    @Autowired
    protected CustomerExtendsRepository customerExtendsRepository;

    /**
     * 查询 小程序首页banner
     * @return
     */
    public List<Ad> listBanner() {
        return customerExtendsRepository.findAll(Ad.class, null, new Object[]{}, null, 0, 0);
    }

    /**
     * 查询 小程序首页渠道
     * @return
     */
    public List<Channel> listChannel() {
        return customerExtendsRepository.findAll(Channel.class, null, new Object[]{}, null, 0, 0);
    }


    /**
     * 查询 小程序首页品牌列表
     * @return
     */
    public List<Brand> listBrand() {
        return customerExtendsRepository.findAll(Brand.class, null, new Object[]{}, null, 0, 0);
    }


}