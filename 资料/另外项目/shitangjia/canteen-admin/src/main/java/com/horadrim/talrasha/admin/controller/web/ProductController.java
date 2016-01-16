package com.horadrim.talrasha.admin.controller.web;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.horadrim.talrasha.admin.controller.result.DataTableRes;
import com.horadrim.talrasha.admin.model.CanteenUser;
import com.horadrim.talrasha.common.CommonParamDefined;
import com.horadrim.talrasha.common.model.Product;
import com.horadrim.talrasha.common.model.ProductCategory;
import com.horadrim.talrasha.common.response.json.AbstractJsonRes;
import com.horadrim.talrasha.common.response.json.ListSucRes;
import com.horadrim.talrasha.common.service.ProductCategoryService;
import com.horadrim.talrasha.common.service.ProductService;
import com.horadrim.talrasha.common.service.StorageService;
import com.horadrim.talrasha.common.util.CheckCodeUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2015/6/18.
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductCategoryService productCategoryService;

    @Resource
    private ProductService productService;
    @Resource
    private StorageService storageService;

    /**
     * 添加 ProductCateGory 菜品种类
     * @return
     */
    @RequestMapping("/addProductCateGory")
    public String addProductCategory(ProductCategory productCategory,HttpSession session){
        CanteenUser canteenUser = (CanteenUser)session.getAttribute(CommonParamDefined.USER);
        productCategory.setCanteenId(canteenUser.getCanteenId());
        productCategoryService.save(productCategory);
        return "redirect:/canteenUser/choicepage?id=8";
    }

    /**
     * 修改菜品类别前根据id读取菜品
     * @param id
     * @return
     */
    @RequestMapping("/getProductCategory")
    public ModelAndView getProductCategory(int id){
        ProductCategory productCategory = productCategoryService.get(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("uptadeProductCategory");
        modelAndView.addObject("productCategory",productCategory);
        return modelAndView;
    }

    /**
     *  修改菜品类别
     * @param productCategory
     * @param session
     * @return
     */
    @RequestMapping("/updateProductCategory")
    public String uptadeProductCategory(ProductCategory productCategory,HttpSession session){
        CanteenUser canteenUser = (CanteenUser)session.getAttribute(CommonParamDefined.USER);
        productCategory.setCanteenId(canteenUser.getCanteenId());
        productCategoryService.update(productCategory);
        return "redirect:/canteenUser/choicepage?id=8";
    }

    /**
     * 查询菜单列表
     * @param session
     * @return
     */
    @RequestMapping("/listProduct")
    @ResponseBody
    public AbstractJsonRes listPorduct(HttpSession session,@DateTimeFormat(pattern = "yyyy-MM-dd")Date date){
        CanteenUser canteenUser = (CanteenUser)session.getAttribute(CommonParamDefined.USER);
        List<Product> products = productService.queryProducts(canteenUser.getCanteenId(),date);
        ListSucRes res = new ListSucRes();
        res.setData(products);
        return res;
    }

    /**
     * 测试 dataTable数据
     * @param aoData
     * @return
     */
    @RequestMapping("/tableDemoAjax")
    @ResponseBody
    public DataTableRes tableDemoAjax(@RequestParam String aoData) {
        String sEcho = null;
        int iDisplayStart = 0; // 起始索引
        int iDisplayLength = 0; // 每页显示的行数
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String,Object>> mapList =null;
        try {
            mapList = mapper.readValue(aoData,mapper.getTypeFactory().constructParametricType(List.class, Map.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mapList==null){
            //错误处理。。。
            return null;
        }
        for (Map obj : mapList){
            if (obj.get("name").equals("sEcho"))
                sEcho = obj.get("value").toString();

            if (obj.get("name").equals("iDisplayStart"))
                iDisplayStart = (int) obj.get("value");

            if (obj.get("name").equals("iDisplayLength"))
                iDisplayLength = (int) obj.get("value");
        }
        // 生成20条测试数据
        List<String[]> lst = new ArrayList<String[]>();
        for (int i = 0; i < 120; i++) {
            String[] d = { "co1_data" + i, "col2_data" + i ,"co12_dadtadtadat"};
            lst.add(d);
        }
        DataTableRes res = new DataTableRes();
        res.setsEcho(sEcho);
        res.setiTotalRecords(lst.size());
        res.setiTotalDisplayRecords(lst.size());
        res.setAaData( lst.subList(iDisplayStart,iDisplayStart + iDisplayLength));
        return res;
    }


    @RequestMapping("/testData")
    @ResponseBody
    public String testNewDataTable(){
    String data = "{\n" +
            "  \"draw\": 2,\n" +
            "  \"recordsTotal\": 11,\n" +
            "  \"recordsFiltered\": 11,\n" +
            "  \"data\": [\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"firstName\": \"Troy\",\n" +
            "      \"lastName\": \"Young\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 2,\n" +
            "      \"firstName\": \"Alice\",\n" +
            "      \"lastName\": \"LL\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 3,\n" +
            "      \"firstName\": \"Larry\",\n" +
            "      \"lastName\": \"Bird\"\n" +
            "    }\n" +
            "  ]\n" +
            "}"      ;
        return data;
    }

    /**
     * 删除product
     * @param id
     * @return
     */
    @RequestMapping("/deleteProduct")
    public ModelAndView deleteProduct(int id){
        productService.deleteProduct(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product");
        return  modelAndView;
    }

    /**
     *  将菜单数据封装成为 DataTable 格式数据
     * @param aoData
     * @param session
     * @return
     */
    @RequestMapping("/productListsPage")
    @ResponseBody
    public DataTableRes productListsPage(@RequestParam String aoData,HttpSession session,@DateTimeFormat(pattern = "yyyy-MM-dd")Date searchDate){
        String sEcho = null;
        int iDisplayStart = 0; // 起始索引
        int iDisplayLength = 0; // 每页显示的行数
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String,Object>> mapList =null;
        try {
            mapList = mapper.readValue(aoData,mapper.getTypeFactory().constructParametricType(List.class, Map.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mapList==null){
            //错误处理。。。
            return null;
        }
        for (Map obj : mapList){
            if (obj.get("name").equals("sEcho"))
                sEcho = obj.get("value").toString();

            if (obj.get("name").equals("iDisplayStart"))
                iDisplayStart = (int) obj.get("value");

            if (obj.get("name").equals("iDisplayLength"))
                iDisplayLength = (int) obj.get("value");
        }

        List<Product> productList =new ArrayList<Product>();
        CanteenUser canteenUser = (CanteenUser)session.getAttribute(CommonParamDefined.USER);

        int size=0;
        if(searchDate==null) {
           productList = productService.queryProducts(canteenUser.getCanteenId(), null, 0, iDisplayStart / iDisplayLength, iDisplayLength);
            size=productService.count("select count(id) from Product where deleteFlag = 1",null).intValue();
        }
        else {
            productList = productService.queryProducts(canteenUser.getCanteenId(), searchDate, 0, iDisplayStart / iDisplayLength, iDisplayLength);
            size=productService.count("select count(id) from Product where deleteFlag = 1 and createdTime =?", new Object[]{searchDate}).intValue();
        }
//        System.out.println("数据"+productList);

        System.out.println("size:"+size);
        List<String[]> lst = new ArrayList<String[]>();
        String temp="";
        for (int i = 0; i < productList.size(); i++) {
            if(productList.get(i).getTimeNode() == 1){
               temp="早餐";
            }
            else if(productList.get(i).getTimeNode() == 2){
                temp = "午餐";
            }
            else{
                temp = "晚餐";
            }
            System.out.println(temp);
            String[] dts = {"<img width=50 height=40 src="+"http://qingcai-images.bj.bcebos.com"+productList.get(i).getImg()+">",productList.get(i).getName(),""+productList.get(i).getPrice(),productList.get(i).getDescription(),productList.get(i).getCreatedTime().toString().substring(0,10),temp,productList.get(i).getProductCategory().getCategoryName(),"<input type='button' value='删除' onclick='deleteProduct("+productList.get(i).getId()+")'>"+"<input type='button' value='修改' onclick='update("+productList.get(i).getId()+")'>" };
            System.out.println(dts.toString());
            lst.add(dts);
        }




        DataTableRes res = new DataTableRes();
        res.setsEcho(sEcho);
        res.setiTotalRecords(size);
        res.setiTotalDisplayRecords(size);
        res.setAaData(lst);
        return res;
    }




    /**
     * 通过ID 获取菜单信息
     * @param id
     * @return
     */
    @RequestMapping("/getProduct")
    public ModelAndView getProduct(int id){
        ModelAndView modelAndView = new ModelAndView();
        try {
            Product product = productService.get(id);
            List<ProductCategory> productCategories = productCategoryService.findAll();
            modelAndView.setViewName("updateProduct");
            modelAndView.addObject("productCategories",productCategories);
            modelAndView.addObject("product",product);
        }
       catch (Exception e){
           System.out.println(e.getMessage());
       }

        return modelAndView;
    }

    /**
     * 修改菜单信息
     * @return
     */
    @RequestMapping("/updateProduct")
    public ModelAndView updateProduct(int id,@RequestParam(required=false)String name,@RequestParam(required=false)BigDecimal price,@RequestParam(required=false)String description,@DateTimeFormat(pattern = "yyyy-MM-dd")Date createTime,@RequestParam(required=false)int timeNode,@RequestParam(required=false)int productCategoryId,HttpSession session,@RequestParam(required=false)String path,@RequestParam(required=false) MultipartFile img) throws IOException {
        CanteenUser canteenUser = (CanteenUser)session.getAttribute(CommonParamDefined.USER);
        ModelAndView modelAndView =new ModelAndView();
        if(canteenUser == null){
            modelAndView.setViewName("canteenuserLogin");
            return modelAndView;
        }
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setCreatedTime(createTime);
        product.setDeleteFlag(1);
        product.setPrice(price);
        product.setTimeNode(timeNode);
        product.setCanteenId(canteenUser.getCanteenId());
        product.setProductCategory(productCategoryService.get(productCategoryId));
        if (img.getSize()>0 && img.getContentType().indexOf("image/") != 0) {
            product = productService.get(id);
            List<ProductCategory> productCategories = productCategoryService.findAll();
            modelAndView.setViewName("updateProduct");
            modelAndView.addObject("productCategories",productCategories);
            modelAndView.addObject("product",product);
            modelAndView.addObject("errorAlertContent","请验证图片格式，你上传的不是正确的图片格式！");
            modelAndView.setViewName("updateProduct");
            return modelAndView;
        }
        if(img.getSize()>100000){
            product = productService.get(id);
            List<ProductCategory> productCategories = productCategoryService.findAll();
            modelAndView.setViewName("updateProduct");
            modelAndView.addObject("productCategories",productCategories);
            modelAndView.addObject("product",product);
            modelAndView.addObject("errorAlertContent","你所上传的图片超过100k,请重新上传！");
            modelAndView.setViewName("updateProduct");
            return modelAndView;
        }
        if(img.getSize()>0){
            SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
            String newName = sdf.format(new Date()) + new CheckCodeUtil().generateRandomNumberCode();
            path = storageService.putFile(img.getInputStream(),newName,img.getSize(),img.getContentType());
        }
        product.setImg(path);
        productService.update(product);
        modelAndView.setViewName("redirect:/canteenUser/choicepage?id=9");
        return modelAndView;
    }


    /**
     * 保存上传菜单
     * @return
     */
    @RequestMapping("/saveProduct")
    public ModelAndView saveProduct(@RequestParam(required=false) String name,@DateTimeFormat(pattern = "yyyy-MM-dd")Date createTime,@RequestParam(required=false) BigDecimal price,@RequestParam(required=false) int book,@RequestParam(required=false) String description,@RequestParam(required=false) int timeNode,@RequestParam(required=false) int productCategoryId, HttpSession session,@RequestParam(required=false) MultipartFile img) throws IOException {

        CanteenUser canteenUser = (CanteenUser)session.getAttribute(CommonParamDefined.USER);
        ModelAndView modelAndView = new ModelAndView();
        if(canteenUser == null){
            modelAndView.setViewName("canteenuserLogin");
            return modelAndView;
        }
        if (img.getSize()>0 && img.getContentType().indexOf("image/") != 0) {
            modelAndView.addObject("errorAlertContent","请验证图片格式，你上传的不是正确的图片格式！");
            modelAndView.setViewName("addProduct");
            return modelAndView;
        }

        if(img.getSize()>100000){ //图片不超过100k
            modelAndView.addObject("errorAlertContent","你所上传的图片超过100k,请重新上传！");
            modelAndView.setViewName("addProduct");
            return modelAndView;
        }
        String path = "";
         if(img.getSize()>0){
             SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
            String newName = sdf.format(new Date()) + new CheckCodeUtil().generateRandomNumberCode();
             path= storageService.putFile(img.getInputStream(),newName,img.getSize(),img.getContentType());
        }

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setCreatedTime(createTime);
        product.setPrice(price);
        product.setTimeNode(timeNode);
        product.setBook(book);
        product.setDeleteFlag(1);
        product.setCanteenId(canteenUser.getCanteenId());
        ProductCategory productCategory =  productCategoryService.get(productCategoryId);
        product.setProductCategory(productCategory);
        product.setImg(path);
        productService.save(product);
        modelAndView.setViewName("redirect:/canteenUser/choicepage?id=9");
        return modelAndView;
    }
}
