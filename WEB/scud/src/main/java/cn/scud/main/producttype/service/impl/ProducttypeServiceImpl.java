package cn.scud.main.producttype.service.impl;

import cn.scud.main.producttype.dao.ProducttypeDao;
import cn.scud.main.producttype.model.Producttype;
import cn.scud.main.producttype.service.ProducttypeService;
import cn.scud.utils.WebUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/20.
 */
@Service("/producttypeService")
public class ProducttypeServiceImpl implements ProducttypeService {

    @Resource
    private ProducttypeDao producttypeDao;

    @Override
    public void savetype(Producttype producttype) {
        producttype.setTypeToken(WebUtil.getPdtypeToken());
        producttypeDao.savetype(producttype);
    }

    @Override
    public List<Producttype> listTpyes(String storeToken) {
        return producttypeDao.listTpyes(storeToken);
    }

    @Override
    public void deleteType(String storeToken, String typeToken) {
        Map map = new HashMap();
        map.put("storeToken",storeToken);
        map.put("typeToken",typeToken);
        producttypeDao.deleteType(map);
    }
}
