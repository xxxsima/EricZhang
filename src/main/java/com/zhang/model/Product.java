package com.zhang.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.util.Date;

/**
 * @Author: King
 * @Create: 2019-11-30 23:38
 * @Desc:
 **/
public class Product {
    private Long id;
    private String productName;
    private String pleaceOfProduction;
    private String nutritive;
    private String logo;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPleaceOfProduction() {
        return pleaceOfProduction;
    }

    public void setPleaceOfProduction(String pleaceOfProduction) {
        this.pleaceOfProduction = pleaceOfProduction;
    }

    public String getNutritive() {
        return nutritive;
    }

    public void setNutritive(String nutritive) {
        this.nutritive = nutritive;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
