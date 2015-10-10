package com.horadrim.talrasha.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2015/8/20.
 */

@Entity
@Table(name = "qc_application_sign_chain_node")
public class AppLicationSignChainNode extends AbstractDomain {
    private Integer applicationSignChainId;
    private Integer signChainNodeId;
    private Integer approverId;
    private Integer status;

    @Column(name = "application_sign_chain_id", length = 11, nullable = false)
    public Integer getApplicationSignChainId() {
        return applicationSignChainId;
    }

    @Column(name = "sign_chain_node_id", length = 11, nullable = false)
    public Integer getSignChainNodeId() {
        return signChainNodeId;
    }

    @Column(name = "approver_id", length = 11, nullable = false)
    public Integer getApproverId() {
        return approverId;
    }

    @Column(name = "status", length = 11, nullable = false)
    public Integer getStatus() {
        return status;
    }

    public void setApplicationSignChainId(Integer applicationSignChainId) {
        this.applicationSignChainId = applicationSignChainId;
    }

    public void setSignChainNodeId(Integer signChainNodeId) {
        this.signChainNodeId = signChainNodeId;
    }

    public void setApproverId(Integer approverId) {
        this.approverId = approverId;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
