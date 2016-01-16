package com.horadrim.talrasha.site.controller.result;

import com.horadrim.talrasha.common.model.Approver;
import com.horadrim.talrasha.common.model.Order;
import com.horadrim.talrasha.common.model.SignChainNode;
import com.horadrim.talrasha.common.response.json.SuccessJsonRes;

import java.util.List;

/**
 * Created by Administrator on 2015/6/4.
 * 订单查询返回结果
 */
public class GetChainNodeRes extends SuccessJsonRes {
    private List<SignChainNode> nodes;
    private List<Approver> approvers;

    public List<SignChainNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<SignChainNode> nodes) {
        this.nodes = nodes;
    }

    public List<Approver> getApprovers() {
        return approvers;
    }

    public void setApprovers(List<Approver> approvers) {
        this.approvers = approvers;
    }
}
