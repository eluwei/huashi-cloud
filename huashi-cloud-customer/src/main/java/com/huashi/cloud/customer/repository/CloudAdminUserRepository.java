package com.huashi.cloud.customer.repository;

import com.huashi.cloud.customer.admin.domain.Admin;

public interface CloudAdminUserRepository extends com.huashi.cloud.common.repository.BaseRepository<Admin, Integer> {

    /**
     * 根据 admin 的用户名 查找admin用户
     * @param userName
     * @return
     */
    public Admin findAdminByUserName(String userName);

}
