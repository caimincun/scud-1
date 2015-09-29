package cn.scud.main.receipt.service;

import cn.scud.main.receipt.model.Receipt;

import java.util.List;

/**
 * Created by Administrator on 2015/9/27.
 */
public interface ReceiptService {

    /**
     * 保存收货信息
     */
    void saveReceipt(Receipt receipt);

    /**
     * 查询 客户相关的收货信息
     * @param userToken
     * @return
     */
    List<Receipt> listReceipt(String userToken);

    /**
     * 删除选中 收货信息
     * @param id
     */
    void delReceipt(int id);
}
