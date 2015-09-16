package cn.scud.main.product.service.impl;

import cn.scud.main.product.dao.ProductDao;
import cn.scud.main.product.model.Product;
import cn.scud.main.product.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/9/15.
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductDao productDao;

    @Override
    public void saveProduct(Product product) {
        productDao.saveProduct(product);
    }

    @Override
    public List<Product> listPorducts(String userToken) {
        return productDao.listPorducts(userToken);
    }

    @Override
    public Product loadProduct(String productToken) {
        return productDao.loadProduct(productToken);
    }
}
