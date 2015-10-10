package com.horadrim.talrasha.common.model;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class AbstractDomain {
	private int id;
    private Date createdDateTime;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "created_date_time",nullable = true)
    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }
}
