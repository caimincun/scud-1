package com.horadrim.talrasha.site.service.impl;

import com.horadrim.talrasha.common.CodeDefined;
import com.horadrim.talrasha.common.dao.ConsumeHistoryDao;
import com.horadrim.talrasha.common.model.ConsumeHistory;
import com.horadrim.talrasha.common.model.User;
import com.horadrim.talrasha.common.service.impl.UserServiceImpl;
import com.horadrim.talrasha.common.util.SplitDateUtil;
import com.horadrim.talrasha.common.util.StringUtils;
import com.horadrim.talrasha.site.service.ClientUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2015/6/4.
 * 客户端用户service
 */
@Service( "clientUserService")
public class ClientUserServiceImpl extends UserServiceImpl implements ClientUserService {

    private static final Logger logger = LoggerFactory.getLogger(ClientUserServiceImpl.class);
    @Resource
    private ConsumeHistoryDao consumeHistoryDao;

    @Override
    public User getByOpenId(String openId) {
        final String hql = "from User where openId=?";
        return userDao.query(hql,new Object[]{openId});
    }


    @Override
    public int payment(int userId, BigDecimal payable,PayType payType,String payPwd, int targetId) {
//      synchronized (this){
        User user = userDao.get(userId);
        if (user==null)
            return CodeDefined.USER_INFO_NULL;
        //判断金额是否大于50
        if (payable.compareTo(new BigDecimal(50))==1){
            //大于50 需要支付密码
            String payPwdDb = user.getPayPassword();
            if(StringUtils.isBlank(payPwdDb))
                return CodeDefined.ACCOUNT_USER_PAYPWD_ISNOTSET;
            if(StringUtils.isBlank(payPwd))
                return CodeDefined.ACCOUNT_USER_PAYPWD_ISNULL;
            if (!payPwdDb.equals(DigestUtils.md5Hex(payPwd)))
                return CodeDefined.ACCOUNT_USER_PAYPWD_ERROR;
        }
        //付款
        BigDecimal balance;
        ConsumeHistory.AccountType type;
        if (payType==PayType.QINGCAIBALANCE){
            balance = user.getQingcaiBalance();
            if (balance.compareTo(payable)==-1)
                return CodeDefined.ACCOUNT_USER_INSUFFICIENT_BALANCE; //余额不足
            user.setQingcaiBalance(balance.subtract(payable));
            type = ConsumeHistory.AccountType.PINGTAI;
        }else if (payType==PayType.CANTEENBALANCE){
            balance = user.getCanteenBalance();
            if (balance.compareTo(payable)==-1)
                return CodeDefined.ACCOUNT_USER_INSUFFICIENT_BALANCE;
            user.setCanteenBalance(balance.subtract(payable));
            type =  ConsumeHistory.AccountType.SHITANG;
        }else{
            return CodeDefined.ACCOUNT_USER_PAYTYPE_WRONG;
        }
        userDao.update(user);
//     }
        //保存记录
        ConsumeHistory history = new ConsumeHistory();
        history.setUserId(userId);
        history.setBalance(payable);
        history.setTargetId(targetId);
        history.setAccountType(type);
        history.setConsumeType(ConsumeHistory.ConsumeType.DIANCAN);
        Date date = new Date();
        history.setConsumeTime(date);
        SplitDateUtil.setYMD(history,date);
        consumeHistoryDao.save(history);
        return CodeDefined.SUCCESS;
    }

    @Override
    public int payment(int userId, BigDecimal payable, PayType payType, int targetId) {
        return payment(userId,payable,payType,null,targetId);
    }

    public enum PayType{
        /**
         * 清菜账户
         */
        QINGCAIBALANCE,
        /**
         * 食堂账户
         */
        CANTEENBALANCE;
    }
}
