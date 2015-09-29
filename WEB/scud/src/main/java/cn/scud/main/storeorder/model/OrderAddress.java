package cn.scud.main.storeorder.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/9/23.
 * 订单的收货人信息
 */
public class OrderAddress implements Serializable{

    private int id;
    //收货地址
    private String address;
    //联系方式
    private String phone;
    //联系人称呼
    private String orderUsname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrderUsname() {
        return orderUsname;
    }

    public void setOrderUsname(String orderUsname) {
        this.orderUsname = orderUsname;
    }

    @Override
    public String toString() {
        return "OrderAddress{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", orderUsname='" + orderUsname + '\'' +
                '}';
    }
}
