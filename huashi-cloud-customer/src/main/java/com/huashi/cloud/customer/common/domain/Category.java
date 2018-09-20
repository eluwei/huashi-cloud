package com.huashi.cloud.customer.common.domain;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * table name:  huashi_goods
 * author name: paul
 * create time: 2018-06-28 09:02:32
 */
@Entity
@Table(name = "huashi_category")
public class Category {
    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "native")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "keywords")
    private String keywords;

    @Column(name = "front_desc")
    private String frontDesc;

    @Column(name = "parent_id")
    private int parentId;

    @Column(name = "sort_order")
    private int sortOrder;

    @Column(name = "show_index")
    private int showIndex;

    @Column(name = "is_show")
    private Boolean isShow;

    @Column(name = "banner_url")
    private String bannerUrl;

    @Column(name = "icon_url")
    private String iconUrl;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "wap_banner_url")
    private String wapBannerUrl;

    @Column(name = "level")
    private String level;

    @Column(name = "type")
    private int type;

    @Column(name = "front_name")
    private String frontName;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getFrontDesc() {
        return frontDesc;
    }

    public void setFrontDesc(String frontDesc) {
        this.frontDesc = frontDesc;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public int getShowIndex() {
        return showIndex;
    }

    public void setShowIndex(int showIndex) {
        this.showIndex = showIndex;
    }

    public Boolean getShow() {
        return isShow;
    }

    public void setShow(Boolean show) {
        isShow = show;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getWapBannerUrl() {
        return wapBannerUrl;
    }

    public void setWapBannerUrl(String wapBannerUrl) {
        this.wapBannerUrl = wapBannerUrl;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFrontName() {
        return frontName;
    }

    public void setFrontName(String frontName) {
        this.frontName = frontName;
    }
}
