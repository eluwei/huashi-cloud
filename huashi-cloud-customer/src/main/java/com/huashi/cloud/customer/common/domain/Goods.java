package com.huashi.cloud.customer.common.domain;


import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
/**
 * table name:  huashi_goods
 * author name: paul
 * create time: 2018-06-28 09:02:32
 */
@Entity
@Table(name = "huashi_goods")
public class Goods{

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "native")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "goods_number")
    private Integer goodsNumber;

    @Column(name = "keywords")
    private String keywords;

    @Column(name = "goods_brief")
    private String goodsBrief;

    @Column(name = "goods_desc")
    private String goodsDesc;

    @Column(name = "is_on_sale")
    private Boolean isOnSale;
    @Column(name = "add_time")
    private Date addTime;
    @Column(name = "sort_order")
    private Integer sortOrder;
    @Column(name = "is_delete")
    private Boolean isDelete;
    @Column(name = "counter_price")
    private Double counterPrice;
    @Column(name = "extra_price")
    private Double extraPrice;
    @Column(name = "is_new")
    private String isNew;
    @Column(name = "goods_unit")
    private String goodsUnit;
    @Column(name = "primary_pic_url")
    private String primaryPicUrl;
    @Column(name = "list_pic_url")
    private String listPicUrl;
    @Column(name = "retail_price")
    private Double retailPrice;
    @Column(name = "sell_volume")
    private Integer sellVolume;
    @Column(name = "unit_price")
    private Double unitPrice;
    @Column(name = "promotion_desc")
    private String promotionDesc;
    @Column(name = "promotion_tag")
    private String promotionTag;
    @Column(name = "app_exclusive_price")
    private Double appExclusivePrice;
    @Column(name = "is_app_exclusive")
    private Boolean isAppExclusive;
    @Column(name = "is_limited")
    private Boolean isLimited;
    @Column(name = "is_hot")
    private Boolean isHot;
    @Column(name = "category_id")
    private Integer categoryId;
    @Column(name = "brand_id")
    private Integer brandId;

    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return id;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public void setGoodsNumber(Integer goodsNumber){
        this.goodsNumber=goodsNumber;
    }
    public Integer getGoodsNumber(){
        return goodsNumber;
    }
    public void setKeywords(String keywords){
        this.keywords=keywords;
    }
    public String getKeywords(){
        return keywords;
    }
    public void setGoodsBrief(String goodsBrief){
        this.goodsBrief=goodsBrief;
    }
    public String getGoodsBrief(){
        return goodsBrief;
    }
    public void setGoodsDesc(String goodsDesc){
        this.goodsDesc=goodsDesc;
    }
    public String getGoodsDesc(){
        return goodsDesc;
    }
    public void setIsOnSale(Boolean isOnSale){
        this.isOnSale=isOnSale;
    }
    public Boolean getIsOnSale(){
        return isOnSale;
    }
    public void setAddTime(Date addTime){
        this.addTime=addTime;
    }
    public Date getAddTime(){
        return addTime;
    }
    public void setSortOrder(Integer sortOrder){
        this.sortOrder=sortOrder;
    }
    public Integer getSortOrder(){
        return sortOrder;
    }
    public void setIsDelete(Boolean isDelete){
        this.isDelete=isDelete;
    }
    public Boolean getIsDelete(){
        return isDelete;
    }
    public void setCounterPrice(Double counterPrice){
        this.counterPrice=counterPrice;
    }
    public Double getCounterPrice(){
        return counterPrice;
    }
    public void setExtraPrice(Double extraPrice){
        this.extraPrice=extraPrice;
    }
    public Double getExtraPrice(){
        return extraPrice;
    }
    public void setIsNew(String isNew){
        this.isNew=isNew;
    }
    public String getIsNew(){
        return isNew;
    }
    public void setGoodsUnit(String goodsUnit){
        this.goodsUnit=goodsUnit;
    }
    public String getGoodsUnit(){
        return goodsUnit;
    }
    public void setPrimaryPicUrl(String primaryPicUrl){
        this.primaryPicUrl=primaryPicUrl;
    }
    public String getPrimaryPicUrl(){
        return primaryPicUrl;
    }
    public void setListPicUrl(String listPicUrl){
        this.listPicUrl=listPicUrl;
    }
    public String getListPicUrl(){
        return listPicUrl;
    }
    public void setRetailPrice(Double retailPrice){
        this.retailPrice=retailPrice;
    }
    public Double getRetailPrice(){
        return retailPrice;
    }
    public void setSellVolume(int sellVolume){
        this.sellVolume=sellVolume;
    }
    public int getSellVolume(){
        return sellVolume;
    }
    public void setUnitPrice(Double unitPrice){
        this.unitPrice=unitPrice;
    }
    public Double getUnitPrice(){
        return unitPrice;
    }
    public void setPromotionDesc(String promotionDesc){
        this.promotionDesc=promotionDesc;
    }
    public String getPromotionDesc(){
        return promotionDesc;
    }
    public void setPromotionTag(String promotionTag){
        this.promotionTag=promotionTag;
    }
    public String getPromotionTag(){
        return promotionTag;
    }
    public void setAppExclusivePrice(Double appExclusivePrice){
        this.appExclusivePrice=appExclusivePrice;
    }
    public Double getAppExclusivePrice(){
        return appExclusivePrice;
    }
    public void setIsAppExclusive(Boolean isAppExclusive){
        this.isAppExclusive=isAppExclusive;
    }
    public Boolean getIsAppExclusive(){
        return isAppExclusive;
    }
    public void setIsLimited(Boolean isLimited){
        this.isLimited=isLimited;
    }
    public Boolean getIsLimited(){
        return isLimited;
    }
    public void setIsHot(Boolean isHot){
        this.isHot=isHot;
    }
    public Boolean getIsHot(){
        return isHot;
    }
    public void setCategoryId(Integer categoryId){
        this.categoryId=categoryId;
    }
    public Integer getCategoryId(){
        return categoryId;
    }
    public void setBrandId(Integer brandId){
        this.brandId=brandId;
    }
    public Integer getBrandId(){
        return brandId;
    }
}


