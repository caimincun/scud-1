package cn.scud.main.receipt.service.impl;

import cn.scud.main.receipt.dao.ReceiptDao;
import cn.scud.main.receipt.model.Receipt;
import cn.scud.main.receipt.service.ReceiptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/27.
 */
@Service("/receiptServiceImpl")
public class ReceiptServiceImpl implements ReceiptService {

    @Resource
    private ReceiptDao receiptDao;

    @Override
    public void saveReceipt(Receipt receipt) {
        receiptDao.saveReceipt(receipt);
    }

    @Override
    public List<Receipt> listReceipt(String userToken) {
        return receiptDao.listReceipt(userToken);
    }

    @Override
    public void delReceipt(int id) {
        receiptDao.delReceipt(id);
    }

    @Override
    public Receipt getReceipt(String orderToken) {
        return receiptDao.getReceipt(orderToken);
    }
}
