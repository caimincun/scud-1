import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

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
        JPushClient jpushTest = new JPushClient(Secret, AppKey);
//        System.out.println(jpushTest.sendNotificationAll("测试Jpush消息，日狗的帅"));

        jpushTest.buildPushObject_all_alias_alert("userToken201508301650136515849","dddd");
//        jpushTest.buildPushObject_all_alias_alert("userToken201508301650136515849","测试推送");

    }


}
