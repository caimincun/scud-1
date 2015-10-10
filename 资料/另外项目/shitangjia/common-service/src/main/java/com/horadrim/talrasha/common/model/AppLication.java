package com.horadrim.talrasha.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2015/8/20.
 */

@Entity
@Table(name = "qc_application")
public class AppLication extends AbstractDomain {

    private Integer userId;
    private String title;
    private String reason;
    private Date eatDate;
    private Integer peopleCount;
    private BigDecimal standardPrice;
    private String fileName;


    @Column(name = "canteen_id", length = 11, nullable = false)
    public Integer getUserId() {
        return userId;
    }

    @Column(name = "user_id", length = 11, nullable = false)
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "title", length = 22, nullable = false)
    public String getTitle() {
        return title;
    }

    @Column(name = "reason", length = 1024, nullable = false)
    public String getReason() {
        return reason;
    }

    @Column(name = "eat_date",nullable = false)
    public Date getEatDate() {
        return eatDate;
    }

    @Column(name = "people_count", length = 2, nullable = false)
    public Integer getPeopleCount() {
        return peopleCount;
    }

    @Column(name = "standard_price", nullable = false)
    public BigDecimal getStandardPrice() {
        return standardPrice;
    }

    @Column(name = "file_name", length = 11, nullable = false)
    public String getFileName() {
        return fileName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setEatDate(Date eatDate) {
        this.eatDate = eatDate;
    }

    public void setPeopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
    }

    public void setStandardPrice(BigDecimal standardPrice) {
        this.standardPrice = standardPrice;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
