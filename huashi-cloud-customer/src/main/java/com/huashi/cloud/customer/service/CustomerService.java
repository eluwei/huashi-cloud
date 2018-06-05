package com.huashi.cloud.customer.service;


import com.huashi.cloud.common.page.PageBean;
import com.huashi.cloud.customer.domain.Customer;
import com.huashi.cloud.customer.repository.CustomerExtendsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class CustomerService {

    @Autowired
    protected CustomerExtendsRepository customerExtendsRepository;

    public Object listCustomer() {
        PageBean pageBean = new PageBean();
        return customerExtendsRepository.executeScalar("select count(*) from t_customer_account where id = ?", new Object[]{"12"} );
    }


}