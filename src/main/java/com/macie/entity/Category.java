package com.macie.entity;

import java.util.Date;

/**
 * @author Macie
 * @date 2020/10/8 -18:32
 */
public class Category {
    private Integer categoryId;
    private String categoryName;
    private Date createDate;
    private Integer categoryDelete;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCategoryDelete() {
        return categoryDelete;
    }

    public void setCategoryDelete(Integer categoryDelete) {
        this.categoryDelete = categoryDelete;
    }
}
