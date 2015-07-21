import cn.scud.commoms.jsonModel.JsonPioContent;
import cn.scud.commoms.jsonModel.JsonPioSearch;
import cn.scud.commoms.jsonModel.JsonPioSimple;
import cn.scud.main.user.model.UserInfo;
import cn.scud.main.user.service.UserService;
import cn.scud.utils.LbsHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@Component
public class JsonSerTest {


    @Resource
    private UserService userService;

    @Test
     public void test(){
//         String lng = "104.094664";
//         String lat = "30.654407";
//         int radius = 100000;
//         JsonPioSearch jsonPioSearch = LbsHelper.pioSearch(lng, lat, radius);
        String parma ="geotable_id=113321&ak=YANNPWadDPvvzTOZGWzXl0Rt" +
                "&id=1044225445668&location=104.094664,30.654407&radius=100000&sortby=distance:1";
        String sr= LbsHelper.sendGet("http://api.map.baidu.com/geosearch/v3/nearby",parma);
        Gson gson = new Gson();
        Type type = new TypeToken<JsonPioSearch>() {
        }.getType();
        JsonPioSearch jsonPioSearch = gson.fromJson(sr, type);
        System.out.println("jsonPo:"+jsonPioSearch);
         List<JsonPioContent> jsonPioContents = jsonPioSearch.getContents();
         List userPoiIds = new ArrayList();
         for(JsonPioContent jsonPioContent:jsonPioContents){
             System.out.println("id:"+jsonPioContent.getUid());
             userPoiIds.add(jsonPioContent.getUid());
         }
        System.out.println(userPoiIds);
         List<UserInfo> userInfos = userService.searchNearbyPoi(userPoiIds); // 取得附近人的信息，但是还需要把 jsonPioSearch 记录里面的 距离添加进去
         for(JsonPioContent jsonPioContent:jsonPioContents){
             for(UserInfo userInfo:userInfos){
                 if(jsonPioContent.getUid() == userInfo.getLbsId()){
                     userInfo.setDistance(jsonPioContent.getDistance());
                     break;
                 }
             }
         }
         System.out.println(userInfos);
     }



}
