import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2015/8/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class JpushTest {

    @Test
    public void testSendMessage() throws APIConnectionException, APIRequestException {
         String AppKey = "a6a19fb2a7aa32214753d0cf";
           String Secret = "cda017b5c004da0bdea5b82e";
        JPushClient jpushTest = new JPushClient(Secret,AppKey);
//        jpushTest.sendNotificationAll("测试Jpush消息，日狗的帅");
        System.out.println(jpushTest.sendNotificationAll("测试Jpush消息，日狗的帅"));
    }
}
