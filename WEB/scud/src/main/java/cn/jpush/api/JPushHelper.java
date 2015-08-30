package cn.jpush.api;

import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

import java.util.Map;

/**
 * Created by Administrator on 2015/8/28.
 */
public class JPushHelper {

    public static final String AppKey = "a6a19fb2a7aa32214753d0cf";
    public static final String  Secret = "cda017b5c004da0bdea5b82e";

    public static  final PushClient pushClient;

    static {
        pushClient = new PushClient(Secret, AppKey);
    }

    /**
     * 向所有的用户发送消息
     * @param alert
     * @return
     * @throws APIConnectionException
     * @throws APIRequestException
     */
    public static PushResult sendNotificationAll(String alert) throws APIConnectionException, APIRequestException {
        PushPayload payload = PushPayload.alertAll(alert);
        return pushClient.sendPush(payload);
    }

    /**
     * Shortcut 向android 用户，通过 别名发送 消息
     */
    public PushResult sendAndroidNotificationWithAlias(String title, String alert,
                                                       Map<String, String> extras, String... alias)
            throws APIConnectionException, APIRequestException {
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.android(alert, title, extras))
                .build();
        return pushClient.sendPush(payload);
    }


}
