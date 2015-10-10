package com.horadrim.talrasha.site.wechat.inf;


import com.horadrim.talrasha.site.wechat.bean.InMessage;
import com.horadrim.talrasha.site.wechat.bean.OutMessage;
import com.horadrim.talrasha.site.wechat.bean.TextOutMessage;

/**
 * Created by Administrator on 2015/5/29.
 */
public class MessageProcessingHandlerImpl implements MessageProcessingHandler {
    private OutMessage outMessage;
    @Override
    public void allType(InMessage msg) {

    }

    @Override
    public void textTypeMsg(InMessage msg) {

    }

    @Override
    public void locationTypeMsg(InMessage msg) {

    }

    @Override
    public void imageTypeMsg(InMessage msg) {

    }

    @Override
    public void videoTypeMsg(InMessage msg) {

    }

    @Override
    public void linkTypeMsg(InMessage msg) {

    }

    @Override
    public void voiceTypeMsg(InMessage msg) {

    }

    @Override
    public void verifyTypeMsg(InMessage msg) {

    }

    @Override
    public void eventTypeMsg(InMessage msg) {
        System.out.println("event msg "+msg.toString());
        if ("subscribe".equals(msg.getEvent())){
            outMessage = new TextOutMessage("食堂家，一个帮助食堂在云端安家的互联网平台，我们致力于将传统食堂变身移动互联网时代的超级食堂。在食堂家，上班族的用餐、买菜将变得更便捷、省心、放心。\n" +
                    "加入我们，一起享受云食堂的美好与安心！");
        }
    }

    @Override
    public void afterProcess(InMessage inMsg, OutMessage outMsg) {

    }

    @Override
    public void setOutMessage(OutMessage outMessage) {

    }

    @Override
    public OutMessage getOutMessage() {
        return outMessage;
    }
}
