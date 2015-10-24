package com.horadrim.talrasha.admin.controller.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.horadrim.talrasha.admin.controller.result.DataTableRes;
import com.horadrim.talrasha.admin.model.CanteenUser;
import com.horadrim.talrasha.common.CodeDefined;
import com.horadrim.talrasha.common.CommonParamDefined;
import com.horadrim.talrasha.common.model.Vegetable;
import com.horadrim.talrasha.common.model.VegetableCategory;
import com.horadrim.talrasha.common.model.VegetableOrder;
import com.horadrim.talrasha.common.model.VegetableOrderItem;
import com.horadrim.talrasha.common.response.json.AbstractJsonRes;
import com.horadrim.talrasha.common.response.json.ErrorJsonRes;
import com.horadrim.talrasha.common.response.json.GetObjSucRes;
import com.horadrim.talrasha.common.response.json.SuccessJsonRes;
import com.horadrim.talrasha.common.service.*;
import com.horadrim.talrasha.common.util.CheckCodeUtil;
import com.horadrim.talrasha.common.util.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2015/7/8.
 */
@Controller
@RequestMapping("/store")
public class StoreController {

    @Resource
    private VegetableService vegetableService;

    @Resource
    private  VegetableCategoryService vegetableCategoryService;

    @Resource
    private StorageService storageService;

    @Resource
    private VegetableOrderItemService vegetableOrderItemService;

    @Resource
    private VegetableOrderService vegetableOrderService;

    @RequestMapping("/saveVegetable")
    public ModelAndView saveVegetable(HttpSession session,@RequestParam(required=false)int vegetableCategoryId,@RequestParam(required=false)String description,@RequestParam(required=false)@DateTimeFormat(pattern = "yyyy-MM-dd")Date saleTime,@RequestParam(required=false)String vegetableName,@RequestParam(required=false)BigDecimal unitPrice,@RequestParam(required=false)String unitName,@RequestParam(required=false)String vegetableDescribe,@RequestParam(required=false)int surplusNum,@RequestParam(required=false) MultipartFile img) throws ParseException, IOException {
        ModelAndView modelAndView = new ModelAndView();
        if (img.getSize()>0 && img.getContentType().indexOf("image/") != 0) {
            modelAndView.addObject("errorAlertContent","请验证图片格式，你上传的不是正确的图片格式！");
            modelAndView.setViewName("store");
            return modelAndView;
        }
        if(img.getSize()>100000){ //图片不超过100k
            modelAndView.addObject("errorAlertContent","你所上传的图片超过100k,请重新上传！");
            modelAndView.setViewName("store");
            return modelAndView;
        }
            String path = "";
         if(img.getSize()>0){
            SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
            String newName = sdf.format(new Date()) + new CheckCodeUtil().generateRandomNumberCode();
             path = storageService.putFile(img.getInputStream(),newName,img.getSize(),img.getContentType());
        }
        Vegetable vegetable = new Vegetable();
        vegetable.setImg(path);
        vegetable.setVegetableCategoryId(vegetableCategoryId);
        vegetable.setVegetableName(vegetableName);
        vegetable.setUnitPrice(unitPrice);
        vegetable.setUnitName(unitName);
        vegetable.setDescription(description);
        vegetable.setVegetableDescribe(vegetableDescribe);
        vegetable.setSurplusNum(surplusNum);
        CanteenUser canteenUser = (CanteenUser) session.getAttribute(CommonParamDefined.USER);
        vegetable.setCanteenId(canteenUser.getCanteenId());
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = sdf.parse(saleTime);
        vegetable.setSaleTime(saleTime);
        vegetableService.save(vegetable);
        modelAndView.setViewName("redirect:/canteenUser/choicepage?id=13");
        return  modelAndView;

    }

    @RequestMapping("/vetetableList")
    @ResponseBody
    public DataTableRes vetetableList( @RequestParam String aoData,HttpSession session,@RequestParam(required=false)Integer vagetableCategoryId){
        CanteenUser user = (CanteenUser) session.getAttribute(CommonParamDefined.USER);
        if (user==null)
            return null;
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

        List<Vegetable> vegetables = null;
        Long size = null;
        String hqlcate = "select * from qc_vegetable_category where canteen_id ="+user.getCanteenId();
        List<VegetableCategory> vegetableCategories = vegetableCategoryService.listBySQL(hqlcate,null);
        if(null == vagetableCategoryId){
            vegetables = vegetableService.listByCanteen(user.getCanteenId(),iDisplayStart / iDisplayLength, iDisplayLength);
            size = vegetableService.count("select count(id) from Vegetable where canteenId ="+user.getCanteenId(),null);
        }
        else{
            vegetables = vegetableService.listByVegaId(user.getCanteenId(),iDisplayStart / iDisplayLength, iDisplayLength,vagetableCategoryId);
            size = vegetableService.count("select count(id) from Vegetable where canteenId ="+user.getCanteenId()+" and vegetableCategoryId ="+vagetableCategoryId,null);
        }

        List<String[]> lst = new ArrayList<>();
        for(int i=0;i<vegetables.size();i++){
            String vegtabCa = "";
            for(VegetableCategory vegetableCategory:vegetableCategories){
                if(vegetableCategory.getId() == vegetables.get(i).getVegetableCategoryId()){
                    vegtabCa = vegetableCategory.getCategoryName();
                    break;
                }
            }
//            String istemp = "";
//            Calendar c= Calendar.getInstance();
//            int y=c.get(Calendar.YEAR);
//            int m=c.get(Calendar.MONTH);
//            int d=c.get(Calendar.DATE);
//            String date=y+"-"+m+"-"+d;
//            Date now=java.sql.Date.valueOf(date);
//            Date saleTime = java.sql.Date.valueOf(vegetables.get(i).getSaleTime().toString().substring(0,10));
//            if(now.before(saleTime)){
//                istemp = "在售";
//            }
//            else{
//
//                istemp = "过期";
//            }
            String[] dts = {"<img width=50 height=40 src="+"http://qingcai-images.bj.bcebos.com"+vegetables.get(i).getImg()+">",vegetables.get(i).getVegetableName(),vegtabCa,"￥"+vegetables.get(i).getUnitPrice()+"元",vegetables.get(i).getUnitName(),vegetables.get(i).getVegetableDescribe(),vegetables.get(i).getSurplusNum()+"",vegetables.get(i).getSaleTime().toString().substring(0,10),"<input type='submit' value='删除' onclick='delVegetab("+vegetables.get(i).getId()+")'>"+"<input type='submit' value='修改' onclick='updateVegetab("+vegetables.get(i).getId()+")'>"};
            lst.add(dts);
        }
        DataTableRes res = new DataTableRes();
        res.setsEcho(sEcho);
        res.setiTotalRecords(size.intValue());
        res.setiTotalDisplayRecords(size.intValue());
        res.setAaData(lst);

        return res;
    }

    @RequestMapping("/getVegetable")
    public ModelAndView getVegetable(int vegtableId){
        Vegetable vegetable = vegetableService.get(vegtableId);
        List<VegetableCategory> vegetableCategories = vegetableCategoryService.findAll();
       
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("vegetable",vegetable);
        modelAndView.addObject("vegetableCategories",vegetableCategories);
        modelAndView.setViewName("updateVegetable");
        return modelAndView;
    }

    @RequestMapping("/delVegetab")
    public ModelAndView delVegetab(int vegtableId){
        vegetableService.delete(vegtableId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/canteenUser/choicepage?id=13");
        return modelAndView;

    }

    @RequestMapping("/updateVegetable")
    @ResponseBody
   public ModelAndView updateVegetable(@RequestParam(required=false)String path,int id,int canteenId,@DateTimeFormat(pattern = "yyyy-MM-dd")Date saleTime,@RequestParam(required=false)String description,@RequestParam(required=false)int vegetableCategoryId,@RequestParam(required=false)String vegetableName,@RequestParam(required=false)BigDecimal unitPrice,@RequestParam(required=false)String unitName,@RequestParam(required=false)String vegetableDescribe,@RequestParam(required=false)int surplusNum,@RequestParam(required=false) MultipartFile img) throws IOException, ParseException {

        ModelAndView modelAndView = new ModelAndView();
        if (img.getSize()>0 && img.getContentType().indexOf("image/") != 0) {
            Vegetable vegetable = vegetableService.get(id);
            List<VegetableCategory> vegetableCategories = vegetableCategoryService.findAll();
            modelAndView.addObject("errorAlertContent","请验证图片格式，你上传的不是正确的图片格式！");
            modelAndView.addObject("vegetable",vegetable);
            modelAndView.addObject("vegetableCategories",vegetableCategories);
            modelAndView.setViewName("updateVegetable");
            return modelAndView;
        }
        if(img.getSize()>100000){ //图片不超过100k
            Vegetable vegetable = vegetableService.get(id);
            List<VegetableCategory> vegetableCategories = vegetableCategoryService.findAll();
            modelAndView.addObject("errorAlertContent", "你所上传的图片超过100k,请重新上传！");
            modelAndView.addObject("vegetable",vegetable);
            modelAndView.addObject("vegetableCategories",vegetableCategories);
            modelAndView.setViewName("updateVegetable");
            return modelAndView;
        }

        if(img.getSize()>0){
            SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
            String newName = sdf.format(new Date()) + new CheckCodeUtil().generateRandomNumberCode();
            path = storageService.putFile(img.getInputStream(),newName,img.getSize(),img.getContentType());
        }
        Vegetable vegetable = new Vegetable();
        vegetable.setId(id);
        vegetable.setImg(path);
        vegetable.setSaleTime(saleTime);
        vegetable.setVegetableCategoryId(vegetableCategoryId);
        vegetable.setVegetableName(vegetableName);
        vegetable.setUnitPrice(unitPrice);
        vegetable.setUnitName(unitName);
        vegetable.setVegetableDescribe(vegetableDescribe);
        vegetable.setSurplusNum(surplusNum);
        vegetable.setDescription(description);
        vegetable.setCanteenId(canteenId);
        vegetableService.update(vegetable);
        modelAndView.setViewName("redirect:/canteenUser/choicepage?id=13");
        return modelAndView;
    }


    /**
     * 根据vegeId 菜品详情
     * @param aoData
     * @param vegeId
     * @return
     */
    @RequestMapping("/getVege")
    @ResponseBody
    public DataTableRes getVege(@RequestParam String aoData,int vegeId){
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

        List<VegetableOrderItem> vegetableOrderItems = vegetableOrderItemService.queryVegPage(iDisplayStart / iDisplayLength, iDisplayLength,vegeId);
        List<String[]> lst = new ArrayList<String[]>();
        for(VegetableOrderItem vegetableOrderItem:vegetableOrderItems){
            String[] dts= {"<img width=50 height=40 src="+"http://qingcai-images.bj.bcebos.com"+vegetableOrderItem.getVegetable().getImg()+">",vegetableOrderItem.getVegetable().getVegetableName(),vegetableOrderItem.getVegetable().getUnitPrice()+"",vegetableOrderItem.getCount()+""};
            lst.add(dts);
        }

        DataTableRes res = new DataTableRes();
        res.setsEcho(sEcho);
        res.setiTotalRecords(vegetableOrderItems.size());
        res.setiTotalDisplayRecords(vegetableOrderItems.size());
        res.setAaData(lst);
        return res;
    }

    /**
     * 查询所有的蔬菜订单
     * @param aoData
     * @return
     */
    @RequestMapping("/listVegeOrders")
    @ResponseBody
    public DataTableRes ListOrders(HttpSession session,@RequestParam String aoData,@RequestParam(required=false)Integer sta,@RequestParam(required=false)Integer pay,@RequestParam(required=false)String order_date){
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
        CanteenUser user = (CanteenUser)session.getAttribute(CommonParamDefined.USER);
        String contSql = "";
        if(pay == null && sta ==null){
            pay = 5;
            sta = 5;  //参数调用传递不能为空，如果传递的参数为null,则赋一个不能是前台传递的值
            contSql = "select count(id) from VegetableOrder where canteenId ="+user.getCanteenId();
        }else{
            contSql = "select count(id) from VegetableOrder where canteenId ="+user.getCanteenId()+" and isPayed ="+pay+" and status ="+sta;
        }
        Long size = null;
        if(null != order_date && !"".equals(order_date)){
            contSql += " and order_date like concat(?,'%')";
            size = vegetableOrderService.count(contSql,new Object[]{order_date});
        }else{
            size = vegetableOrderService.count(contSql,null);
        }
        System.out.println(size);
        List<VegetableOrder> vegetableOrders = vegetableOrderService.queryOrderPage(user.getCanteenId(),sta,pay,iDisplayStart / iDisplayLength, iDisplayLength,order_date);

        List<String[]> lst = new ArrayList<String[]>();
        for(int i = 0; i< vegetableOrders.size() ;i++){
            String stausTemp = "";
            System.out.println(vegetableOrders.get(i).toString());
            if(vegetableOrders.get(i).getStatus() == 0){
                stausTemp = "未付";
            }else{
                stausTemp = "已付";
            }
            String str = "";
            switch (vegetableOrders.get(i).getStatus()){
                case 1:
                    str = "已取菜";
                    break;
                case 2:
                    str = "订单已赠送";
                    break;
                case 3:
                    str = "订单已撤销";
                    break;
                case 0:
                    str = "未取菜";
                    break;
            }
              String[] dts = {vegetableOrders.get(i).getOrderNum()+"",vegetableOrders.get(i).getOrderDate().toString().substring(0, 19), str,vegetableOrders.get(i).getTotalPrice()+"",stausTemp,vegetableOrders.get(i).getNote(),"<input type='submit' value='详情' onclick='detailVegOrder("+vegetableOrders.get(i).getId()+")'>"};
            lst.add(dts);
        }
        DataTableRes res = new DataTableRes();
        res.setsEcho(sEcho);
        res.setiTotalRecords(size.intValue());
        res.setiTotalDisplayRecords(size.intValue());
        res.setAaData(lst);
        return res;
    }


    /**
     * 根据订单号取得订单详情
     * @param orderNum 订单号
     */
    @RequestMapping("/qucai")
    @ResponseBody
    public AbstractJsonRes getOrder(String orderNum,HttpSession session){
        //查询订单
        //
        CanteenUser user = (CanteenUser) session.getAttribute(CommonParamDefined.USER);
        if (user==null)
            return new ErrorJsonRes(CodeDefined.COMMON_NOT_AUTH);
        final String sql = "SELECT o.id AS oid,o.order_num AS orderNum,o.total_price AS totalPrice,o.order_date AS orderDate," +
                "o.`status`,o.is_payed AS isPayed,u.id AS uid,u.phone,u.nickname,i.count AS pcount,p.img, " +
                "p.vegetable_name as name FROM qc_vegetable_order o LEFT JOIN qc_user u ON u.id = o.user_id " +
                "LEFT JOIN qc_vegetable_order_item i ON o.id = i.vegetable_order_id " +
                "LEFT JOIN qc_vegetable p ON i.vegetable_id = p.id " +
                "WHERE o.order_num = ? AND o.deleted = 0  AND o.canteen_id = "+user.getCanteenId();
        @SuppressWarnings("unchecked")
        List<Map<String,Object>> order = vegetableOrderService.listFieldBySQL(sql, new Object[]{orderNum});
        if(order.size()==0){
            return new ErrorJsonRes(4,"查询不到相关信息");
        }
        Map<String,Object> map = order.get(0);
//        int isPayed = (int) map.get("isPayed");
//        if (isPayed==0){
//            return new ErrorJsonRes(4,"订单未支付");
//        }
        int status = (int) map.get("status");
        if (status>0){
            String str = "订单不能取";
            switch (status){
                case 1:
                    str = "订单等待确认支付";
                    break;
                case 2:
                    str = "订单已完成";
                    break;
                case 3:
                    str = "订单已超时";
                    break;
                case 4:
                    str = "订单已经被撤销";
                    break;
            }
            return new ErrorJsonRes(4,str);
        }
        //将order设置为已经取餐
//        vegetableOrderService.executeUpdate("UPDATE VegetableOrder SET status=1 , takeDate=? WHERE orderNum=?",new Object[]{new Date(),orderNum});
        GetObjSucRes res = new GetObjSucRes();
        res.setData(order);

        return res;
    }

    /**
     * 取菜时更新蔬菜订单状态 为待确认
     */
    @RequestMapping("/vegetableToWaitConfirm")
    @ResponseBody
    public AbstractJsonRes updateVegetableOrder(String orderNumber,double realMoney){
        if (StringUtils.isBlank(orderNumber))
            return new ErrorJsonRes(4,"订单号不能为空");
        if (realMoney<0)
            return new ErrorJsonRes(4,"请正确填写实际金额");
        VegetableOrder order = vegetableOrderService.getByOrderNum(orderNumber);
        if (order==null)
            return new ErrorJsonRes(4,"订单不存在");
        if (order.getStatus()!=0){
            String str = "订单不能取";
            switch (order.getStatus()){
                case 1:
                    str = "订单等待确认支付";
                    break;
                case 2:
                    str = "订单已完成";
                    break;
                case 3:
                    str = "订单已超时";
                    break;
                case 4:
                    str = "订单已经被撤销";
                    break;
            }
            return new ErrorJsonRes(4,str);
        }
        vegetableOrderService.executeUpdate("UPDATE VegetableOrder SET status=1,totalPrice=? WHERE id=?",
                new Object[]{new BigDecimal(realMoney),order.getId()});
        return new SuccessJsonRes();
    }

}
