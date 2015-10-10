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
@Table(name = "qc_application_sign_chain")
public class AppLicationSignChain extends AbstractDomain {
    private Integer applicationId;
    private Integer signChainId;

    @Column(name = "application_id", length = 11, nullable = false)
    public Integer getApplicationId() {
        return applicationId;
    }
    @Column(name = "sign_chain_id", length = 11, nullable = false)
    public Integer getSignChainId() {
        return signChainId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public void setSignChainId(Integer signChainId) {
        this.signChainId = signChainId;
    }
}
