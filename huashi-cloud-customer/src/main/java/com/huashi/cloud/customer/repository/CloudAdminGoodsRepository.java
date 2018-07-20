package com.huashi.cloud.customer.repository;

import com.huashi.cloud.customer.common.domain.Goods;

public interface CloudAdminGoodsRepository extends com.huashi.cloud.common.repository.BaseRepository<Goods, Integer> {

    /**
     * 根据 Goods 的商品名 查找商品名
     * @param goodName
     * @return
     */
    public Goods findGoodsByName(String goodName);

}
