package com.horadrim.talrasha.common.model;


import javax.persistence.*;

/**
 * Created by Administrator on 2015/8/20.
 */

@Entity
@Table(name = "qc_approver")
public class Approver extends AbstractDomain {

    //private Integer userId;

    private User user;

    @OneToOne(cascade= CascadeType.ALL,fetch= FetchType.EAGER,targetEntity=User.class)
    @JoinColumn(name="user_id",nullable=false,updatable=false)
    public User getUser() {
        return user;
    }

    /*@Column(name = "user_id", length = 11, nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }*/

    public void setUser(User user) {
        this.user = user;
    }
}
