package com.horadrim.talrasha.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Administrator on 2015/7/10.
 */
@Entity
@Table(name = "qc_canteen_message")
public class CanteenMessage extends AbstractDomain {

    public enum MessageType{
        /**
         * 点餐订单
         */
        DIANCAN_ORDER,
        /**
         * 蔬菜订单
         */
        SHUCAI_ORDER;

        public static MessageType getCanteenMsg(int index){
//            MessageType type = null;
            for (MessageType type:MessageType.values()){
                if (type.ordinal()==index){
                    return type;
                }
            }
            return null;
        }

    }

    private int canteenId;
    private int isRead;
    private int targetId;
    private int messageType;  //0 点餐 1蔬菜
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;

    @Column(name = "canteen_id")
    public int getCanteenId() {
        return canteenId;
    }
    @Column(name = "is_read")
    public int getIsRead() {
        return isRead;
    }
    @Column(name = "target_id")
    public int getTargetId() {
        return targetId;
    }
    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }
    @Column(name = "message_type")
    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public void setCanteenId(int canteenId) {
        this.canteenId = canteenId;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}

