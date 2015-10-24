package com.horadrim.talrasha.common.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2015/8/20.
 */

@Entity
@Table(name = "qc_sign_chain")
public class SignChain extends AbstractDomain {
    private Integer  canteenId;
    private String chainName;

    private List<SignChainNode> signChainNodes = new ArrayList<>();

    @OneToMany(cascade = { CascadeType.ALL },fetch = FetchType.EAGER,targetEntity = SignChainNode.class)
    @JoinColumns(value = {@JoinColumn(name = "chain_id",referencedColumnName = "id")})
    public List<SignChainNode> getSignChainNodes() {
        return signChainNodes;
    }

    @Column(name = "chain_name", length = 64, nullable = false)
    public String getChainName() {
        return chainName;
    }

    @Column(name = "canteen_id", length = 11, nullable = false)
    public Integer getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(Integer canteenId) {
        this.canteenId = canteenId;
    }

    public void setChainName(String chainName) {
        this.chainName = chainName;
    }

    public void setSignChainNodes(List<SignChainNode> signChainNodes) {
        this.signChainNodes = signChainNodes;
    }
}
