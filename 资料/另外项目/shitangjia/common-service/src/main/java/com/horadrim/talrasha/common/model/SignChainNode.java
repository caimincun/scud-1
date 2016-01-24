package com.horadrim.talrasha.common.model;

import javax.persistence.*;

/**
 * Created by Administrator on 2015/8/20.
 */

@Entity
@Table(name = "qc_sign_chain_node")
public class SignChainNode extends AbstractDomain {
    //private SignChain  signChain;
    private String nodeName;

    private Integer frontNodeId;

    @Column(name = "front_node_id", length = 11, nullable = true)
    public Integer getFrontNodeId() {
        return frontNodeId;
    }

    public void setFrontNodeId(Integer frontNodeId) {
        this.frontNodeId = frontNodeId;
    }

    /*@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} ,fetch = FetchType.EAGER)
    @JoinColumn(name = "chain_id",nullable = false)
    public SignChain getSignChain() {
        return signChain;
    }*/

    @Column(name = "node_name", length = 64, nullable = false)
    public String getNodeName() {
        return nodeName;
    }

    /*public void setSignChain(SignChain signChain) {
        this.signChain = signChain;
    }*/

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
}
