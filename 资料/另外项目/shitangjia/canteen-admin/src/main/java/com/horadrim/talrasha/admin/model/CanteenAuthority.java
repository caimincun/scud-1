package com.horadrim.talrasha.admin.model;

import com.horadrim.talrasha.common.model.AbstractDomain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2015/6/10.
 */
@Entity
@Table(name = "qc_canteenauthority")
public class CanteenAuthority extends AbstractDomain implements Comparable<CanteenAuthority> {

    private String authorityName;

    private CanteenAuthority canteenAuthority;

//    private Set<CanteenAuthority>  canteenAuthorities =new HashSet<>();

    @ManyToOne //多对一的关联
    @JoinColumn(name="parent_id")//这是自关联的pid列名设置
    public CanteenAuthority getCanteenAuthority() {
        return canteenAuthority;
    }

    public void setCanteenAuthority(CanteenAuthority canteenAuthority) {
        this.canteenAuthority = canteenAuthority;
    }

    @Column(name = "authority_name",nullable = false,length = 100)
    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }
//
//    @OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="canteenAuthority")
//    public Set<CanteenAuthority> getCanteenAuthorities() {
//        return canteenAuthorities;
//    }
//
//    public void setCanteenAuthorities(Set<CanteenAuthority> canteenAuthorities) {
//        this.canteenAuthorities = canteenAuthorities;
//    }

    @Override
    public String toString() {
        return "CanteenAuthority{" +
                "authorityName='" + authorityName + '\'' +
                ", canteenAuthority=" + canteenAuthority +
//                ", canteenAuthorities=" + canteenAuthorities +
                '}';
    }

    @Override
    public int compareTo(CanteenAuthority o) {
        if(this.getId()<o.getId())return -1;
        else if(this.getId()==o.getId())return 0;
        else return 1;
    }

}
