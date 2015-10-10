package com.horadrim.talrasha.admin.controller.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.horadrim.talrasha.admin.controller.result.DataTableRes;
import com.horadrim.talrasha.admin.model.CanteenUser;
import com.horadrim.talrasha.common.CodeDefined;
import com.horadrim.talrasha.common.CommonParamDefined;
import com.horadrim.talrasha.common.model.Order;
import com.horadrim.talrasha.common.model.OrderItem;
import com.horadrim.talrasha.common.response.json.AbstractJsonRes;
import com.horadrim.talrasha.common.response.json.ErrorJsonRes;
import com.horadrim.talrasha.common.response.json.GetObjSucRes;
import com.horadrim.talrasha.common.service.OrderItemService;
import com.horadrim.talrasha.common.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/6/23.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @Resource
    private OrderItemService orderItemService;

    /**
     * 跳转到取餐页
     */
    @RequestMapping("/toQuCanPage")
    public String toQucanPage(){
        return "qucan";
    }

    /**
     * 根据订单号取得订单详情
     * @param orderNum 订单号
     */
    @RequestMapping("/qucan")
    @ResponseBody
    public AbstractJsonRes getOrder(String orderNum,HttpSession session){
        CanteenUser user = (CanteenUser) session.getAttribute(CommonParamDefined.USER);
        if (user==null)
            return new ErrorJsonRes(CodeDefined.COMMON_NOT_AUTH);
        final String sql = "SELECT o.id AS oid,o.order_num AS orderNum,o.total_price AS totalPrice,o.order_date " +
                "AS orderDate,o.`status`,o.is_payed AS isPayed,u.id AS uid,u.phone,u.nickname,i.count AS pcount," +
                "p.img,p.`name` FROM qc_order o LEFT JOIN qc_user u ON u.id = o.user_id" +
                " LEFT JOIN qc_order_item i ON o.id = i.order_id LEFT JOIN qc_product p ON i.product_id = p.id WHERE" +
                " o.order_num = ? AND o.deleted = 0 AND o.canteen_id="+user.getCanteenId();
        @SuppressWarnings("unchecked")
        List<Map<String,Object>> order = orderService.listFieldBySQL(sql, new Object[]{orderNum});
        if(order.size()==0){
            return new ErrorJsonRes(4,"查询不到相关信息");
        }
        Map<String,Object> map = order.get(0);
        int isPayed = (int) map.get("isPayed");
        if (isPayed==0){
            return new ErrorJsonRes(4,"订单未支付");
        }
        int status = (int) map.get("status");
        if (status>0){
            String str = "订单不能取餐";
            switch (status){
                case 1:
                    str = "订单已经取餐";
                    break;
                case 2:
                    str = "订单已经被转赠送";
                    break;
                case 3:
                    str = "订单已经被撤销";
                    break;
            }
            return new ErrorJsonRes(4,str);
        }
        //将order设置为已经取餐
        orderService.executeUpdate("UPDATE Order SET status=1 , takeDate=? WHERE orderNum=?",new Object[]{new Date(),orderNum});
        GetObjSucRes res = new GetObjSucRes();
        res.setData(order);

        return res;
    }

    /**
     * 查询所有的蔬菜订单
     * @return
     */
    @RequestMapping("/vegeAllOrder")
    @ResponseBody
    public DataTableRes vegeAllOrder(@RequestParam String aoData){
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


        String hql = "SELECT order_num from qc_vegetable_order";
        List<Map<String,Object>>  order_nums = orderService.listFieldBySQL(hql,iDisplayStart, iDisplayLength,null);
        List<Map<String,Object>>  sizes = orderService.listFieldBySQL(hql,null);
        System.out.println("order_nums:"+order_nums.size());
        String hql2 = "SELECT o.id AS oid,o.order_num AS orderNum,o.total_price AS totalPrice,o.order_date AS orderDate,o.`status`,o.is_payed AS isPayed,u.id AS uid,u.phone,u.nickname,i.count AS pcount,p.img," +
                "p.vegetable_name as name FROM qc_vegetable_order o LEFT JOIN qc_user u ON u.id = o.user_id LEFT JOIN qc_vegetable_order_item i ON o.id = i.vegetable_order_id" +
                " LEFT JOIN qc_vegetable p ON i.vegetable_id = p.id WHERE o.order_num = ?";
        List<String[]> lst = new ArrayList<String[]>();
        int pagesize = 0;
        int index = iDisplayStart;
        for(int j = 0;j<order_nums.size();j++){
            System.out.println("order_nums.get(j):"+order_nums.get(j).get("order_num"));
            String order_num = order_nums.get(j).get("order_num").toString();
            Object[] params = {order_num};
            List<Map<String,Object>> allOrders = orderService.listFieldBySQL(hql2,params);
            System.out.println("allorders.size():"+allOrders.size());
            if (allOrders.size()==0){
                continue;
            }
            pagesize += allOrders.size();
            String contents = "";
            for(int i =0;i<allOrders.size();i++){
                contents += allOrders.get(i).get("pcount").toString()+"斤"+allOrders.get(i).get("name")+"    ";
            }
            System.out.println("content:"+contents);
            index++;
            String[] dts = {index+"",allOrders.get(0).get("nickname").toString(),allOrders.get(0).get("phone").toString(),contents};
            lst.add(dts);
        }

        DataTableRes res = new DataTableRes();
        res.setsEcho(sEcho);
        res.setiTotalRecords(sizes.size());
        res.setiTotalDisplayRecords(sizes.size());
        res.setAaData(lst);
        return res;
    }

    /**
     * 后台获取订单详细信息
     * @return
     */
    @RequestMapping("/orderDetail")
    @ResponseBody
    public DataTableRes getOrderDeatil(@RequestParam String aoData,int orderId){
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


            List<OrderItem> orderItems = orderItemService.queryPage(iDisplayStart / iDisplayLength, iDisplayLength, orderId);


        List<String[]> lst = new ArrayList<String[]>();
        for(int i = 0;i<orderItems.size();i++){
            String dts[] = {"<img width=50 height=40 src="+"http://qingcai-images.bj.bcebos.com"+orderItems.get(i).getProduct().getImg()+">",orderItems.get(i).getProduct().getName(),orderItems.get(i).getProduct().getPrice()+"",orderItems.get(i).getProduct().getDescription(),orderItems.get(i).getCount()+""};
            lst.add(dts);
        }
        DataTableRes res = new DataTableRes();
        res.setsEcho(sEcho);
        res.setiTotalRecords(orderItems.size());
        res.setiTotalDisplayRecords(orderItems.size());
        res.setAaData(lst);
        return res;
    }

    /**
     * 查询所有订单信息
     * @return
     */
    @RequestMapping("/listOrder")
    @ResponseBody
    public DataTableRes ListOrder(HttpSession session,@RequestParam String aoData,@RequestParam(required=false)Integer sta,@RequestParam(required=false)Integer pay,@RequestParam(required=false)String order_date){
//                System.out.println("后台传递参数："+sta+"   "+ pay);
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
        String contSql = "";
        int ordersCount =0;
        CanteenUser user = (CanteenUser)session.getAttribute(CommonParamDefined.USER);
        if(pay == null && sta ==null){
            pay = 5;
            sta = 5;  //参数调用传递不能为空，如果传递的参数为null,则赋一个不能是前台传递的值
            contSql = "select count(id) from Order where canteenId ="+user.getCanteenId();
        }
       else{
            contSql = "select count(id) from Order where canteenId ="+user.getCanteenId()+" and isPayed ="+pay+" and status ="+sta;
        }
        if(null != order_date && !"".equals(order_date)){
            contSql += " and order_date like concat(?,'%')";
            ordersCount = orderService.count(contSql,new Object[]{order_date}).intValue();
        }
        else {
            ordersCount = orderService.count(contSql,null).intValue();
        }
        System.out.println(ordersCount);

        List<Order> orders  = orderService.queryOrderPage(user.getCanteenId(),sta,pay,iDisplayStart / iDisplayLength, iDisplayLength,order_date);

        List<String[]> lst = new ArrayList<String[]>();
        for(int i = 0;i<orders.size();i++){
            String statusTemp = "";
            if(orders.get(i).getStatus()==0){
                statusTemp = "未取餐";
            }else if(orders.get(i).getStatus() == 1){
                statusTemp = "已取餐";
            }else if(orders.get(i).getStatus() == 2){
                statusTemp = "转赠";
            }else{
                statusTemp = "取消";
            }
            String payTem = "";
            if(orders.get(i).getIsPayed()==0){
                payTem = "未支付";
            }
            else{
                payTem = "已支付";
            }
            String qucantime = "";
            if(orders.get(i).getTakeNode()==1){
                qucantime = "早上";
            }
            else if(orders.get(i).getTakeNode() == 2){
                qucantime = "中午";
            }
            else{
                qucantime = "晚上";
            }

            String takedateTmep = "";
            if(null == orders.get(i).getTakeDate()){
                takedateTmep = "未取餐";
            }else {
                takedateTmep = orders.get(i).getTakeDate().toString().substring(0,10)+"日   "+qucantime;
            }
            String[] dts = {orders.get(i).getOrderNum(),orders.get(i).getOrderDate().toString().substring(0,19),orders.get(i).getUser().getNickName(),statusTemp,orders.get(i).getTotalPrice()+"",payTem,takedateTmep,"<input type='submit' value='详情' onclick='detailOrder("+orders.get(i).getId()+")'>"};
            lst.add(dts);
        }
        DataTableRes res = new DataTableRes();
        res.setsEcho(sEcho);
        res.setiTotalRecords(ordersCount);
        res.setiTotalDisplayRecords(ordersCount);
        res.setAaData(lst);

        return res;
    }
}
