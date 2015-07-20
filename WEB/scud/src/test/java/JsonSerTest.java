import cn.scud.commoms.jsonModel.JsonPioSimple;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2015/7/20.
 */
public class JsonSerTest {


     public static void main(String[] str){
         JsonPioSimple product = new JsonPioSimple();
         Gson gson = new Gson();//new一个Gson对象
         String json = "{\"status\":0,\"id\":1077051852,\"message\":\"成功\"}";
         product = gson.fromJson(json, JsonPioSimple.class);
         //输出
         System.out.println("status:" + product.getStatus());
         System.out.println("Id:" + product.getId());
         System.out.println("Message:" + product.getMessage());
     }


}
