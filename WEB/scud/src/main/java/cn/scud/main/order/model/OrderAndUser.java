package cn.scud.main.order.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/8/6.
 * 订单和用户的中间表，用用于临时保存订单和很多表现出接单意向的人中间关联，当需求发布人确定接单人之后，删除所有的临时数据
 */
public class OrderAndUser implements Serializable {

    private int id;
    private String orderToken;  //订单token
    private String userToken;   // 用户token

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderToken() {
        return orderToken;
    }

    public void setOrderToken(String orderToken) {
        this.orderToken = orderToken;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    @Override
    public String toString() {
        return "OrderAndUser{" +
                "id=" + id +
                ", orderToken='" + orderToken + '\'' +
                ", userToken='" + userToken + '\'' +
                '}';
    }
}
