import cn.scud.commoms.jsonModel.JsonPioContent;
import cn.scud.commoms.jsonModel.JsonPioSearch;
import cn.scud.commoms.jsonModel.JsonPioSimple;
import cn.scud.main.user.model.UserInfo;
import cn.scud.main.user.service.UserService;
import cn.scud.utils.BosHelper;
import cn.scud.utils.LbsHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@Component
public class JsonSerTest {

    public static final String AK = "YANNPWadDPvvzTOZGWzXl0Rt"; //  访问 LBS 数据的权限
    public static final String GEOTABLE_ID = "113562";          //  LBS 数据库的标志
    public static final String PRE_PARAM ="geotable_id="+GEOTABLE_ID+"&ak="+AK+"&coord_type=3&";

    public static final String UPDATE_PIO = "http://api.map.baidu.com/geodata/v3/poi/update"; //跟新数据
    public static final String SAVE_PIO = "http://api.map.baidu.com/geodata/v3/poi/create"; // 保存数据
    public static final String SEARCH_PIO = "http://api.map.baidu.com/geosearch/v3/nearby"; //检索附近人
    @Resource
    private UserService userService;

    @Test
     public void test() throws UnsupportedEncodingException {





//        1. 保存 pio 数据
//        String param ="geotable_id=113321&ak=YANNPWadDPvvzTOZGWzXl0Rt&id=104422544445668&latitude=30.659768&longitude=104.080336" +
//                "&coord_type=3&title=测试条件数据&tags=条件1 条件2";
//        String res2 = LbsHelper.sendPost("http://api.map.baidu.com/geodata/v3/poi/create",param);
//        System.out.println("保存 poi 数据："+LbsHelper.decodeUnicode(LbsHelper.decodeUnicode(res2)));


        // 2. 修改 自己保存 pio 数据的 tags 条件标签
//        String str= LbsHelper.sendPost("http://api.map.baidu.com/geodata/v3/poi/update", "geotable_id=113321&ak=YANNPWadDPvvzTOZGWzXl0Rt&coord_type=3&id=1203364008&tags=条件3");
//        System.out.println(LbsHelper.decodeUnicode(str));


        // 3. 周边 条件 检索 pio .查看附近 ， 发送 get 请求  春熙路数据：  104.086399,30.659378  ，条件检索 自己添加的 条件poi 数据
//        String parma ="geotable_id=113321&ak=YANNPWadDPvvzTOZGWzXl0Rt&id=1044225445668&location=104.094664,30.654407&radius=100000&sortby=distance:1&tags="+ URLEncoder.encode("条件1", "utf-8");
//        //filter 条件过滤,一个条件可以
//        String res = LbsHelper.sendGet("http://api.map.baidu.com/geosearch/v3/nearby",parma);
//        System.out.println(LbsHelper.decodeUnicode(res));







        /* poi 返回数据 格式化 */


         String lng = "104.094664";
        String lat = "30.654407";
        int radius = 100000;
        String  USER_PRE_PARAM ="geotable_id="+113562+"&ak="+AK+"&coord_type=3&";
//        JsonPioSearch jsonPioSearch = LbsHelper.pioSearch(lng, lat, radius);
//        String parma ="geotable_id=113562&ak=YANNPWadDPvvzTOZGWzXl0Rt" +
//                "&id=1096921683&location=104.094664,30.654407&radius=100000&sortby=distance:1&page_index=1&page_size=2";
//        String sr= LbsHelper.sendGet("http://api.map.baidu.com/geosearch/v3/nearby",parma);
//        Gson gson = new Gson();
//        Type type = new TypeToken<JsonPioSearch>() {
//        }.getType();
//        JsonPioSearch jsonPioSearch = gson.fromJson(sr, type);


        String param =USER_PRE_PARAM+"&sortby=distance:1" +"&location="+lng+","+lat+"&radius="+radius+"&page_index="+0+"&page_size="+10;

        String str = LbsHelper.sendGet(SEARCH_PIO, param);
        System.out.println(str);
        JsonPioSearch jsonPioSearch = gsonSeizSearch(str);
        System.out.println(jsonPioSearch.size);


//        JsonPioSearch jsonPioSearch = LbsHelper.pioSearch("104.094664","30.654407",100000,1,2);
//        System.out.println("jsonPo:"+jsonPioSearch);
//         List<JsonPioContent> jsonPioContents = jsonPioSearch.getContents();
//         List userPoiIds = new ArrayList();
//         for(JsonPioContent jsonPioContent:jsonPioContents){
//             System.out.println("id:"+jsonPioContent.getUid());
//             userPoiIds.add(jsonPioContent.getUid());
//         }
//        System.out.println(userPoiIds);
//         List<UserInfo> userInfos = userService.searchNearbyPoi(userPoiIds); // 取得附近人的信息，但是还需要把 jsonPioSearch 记录里面的 距离添加进去,此时是无序的
//         List<UserInfo> userInfoList = new ArrayList<UserInfo>();
//         for(JsonPioContent jsonPioContent:jsonPioContents){
//             for(UserInfo userInfo:userInfos){
//                 if(jsonPioContent.getUid() == userInfo.getLbsId()){
//                     userInfo.setDistance(jsonPioContent.getDistance());
//                     userInfoList.add(userInfo); //将有序由近到远的添加进去
//                     break;
//                 }
//             }
//         }
//         System.out.println(userInfoList);
//        String lat="0";
//        String lng = "0";
//
//        String param = PRE_PARAM+"latitude="+lat+"&longitude="+lng;
//        String str = LbsHelper.sendPost(SAVE_PIO,param);
//        System.out.println(str);


        // 删除bos 图片

//        String path = "/upload/150726184042";
//        BosHelper bosHelper = new BosHelper();
//        bosHelper.deleteObject(path);
     }

    public static JsonPioSearch gsonSeizSearch(String jsonReturn){
        Gson gson = new Gson();
        Type type = new TypeToken<JsonPioSearch>() {
        }.getType();
        return gson.fromJson(jsonReturn, type);
    }

}
