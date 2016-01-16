package com.horadrim.talrasha.site.controller.request;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/6/3.
 */
public class Cart {
    private List<CartItem> items=new ArrayList<CartItem>();
    private BigDecimal totalPrice=new BigDecimal(0);
    private int totalCount=0;

    public int getTotalCount() {
        if(items.size()==0){
            totalCount=0;
        }
        for(CartItem item:items){
            totalCount=totalCount+item.getCount();
        }
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        for(CartItem c:items){
            if(totalPrice==null){
                totalPrice=c.getPrice().multiply(new BigDecimal(c.getCount()));
            }else{
                totalPrice=totalPrice.add(c.getPrice().multiply(new BigDecimal(c.getCount())));
            }
        }
        return totalPrice;
    }
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }


}
