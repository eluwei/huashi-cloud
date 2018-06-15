package com.huashi.cloud.customer.domain;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_customer_account")
public class Customer {
    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "native")
    private Integer id;

    @Column(name = "userType", length = 10, nullable = true)
    private String userType;

    @Column(name = "userName", length = 20, nullable = true)
    private String userName;

    @Column(name = "password", nullable = true)
    private Float password;

    @Column(name = "nickName", length = 20, nullable = true)
    private String nickName;

    @Column(name = "realName", length = 20, nullable = true)
    private String realName;

    @Column(name = "city", length = 10, nullable = true)
    private String city;

    @Column(name = "sex", length = 10, nullable = true)
    private String sex;

    @Column(name = "phone", length = 20, nullable = true)
    private String phone;

    @Column(name = "email", length = 40, nullable = true)
    private String email;

    @Column(name = "headPic", nullable = true)
    private String headPic;

    @Column(name = "createTime", nullable = true)
    private String createTime;

    @Column(name = "lastLoginTime", nullable = true)
    private String lastLoginTime;

    @Column(name = "isDelete", length = 1, nullable = true)
    private Integer isDelete;

    @Column(name = "isBlackList", length = 1, nullable = true)
    private Integer isBlackList;

    @Column(name = "isVerified", length = 1, nullable = true)
    private Integer isVerified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Float getPassword() {
        return password;
    }

    public void setPassword(Float password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getIsBlackList() {
        return isBlackList;
    }

    public void setIsBlackList(Integer isBlackList) {
        this.isBlackList = isBlackList;
    }

    public Integer getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Integer isVerified) {
        this.isVerified = isVerified;
    }
}
