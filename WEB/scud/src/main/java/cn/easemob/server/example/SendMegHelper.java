package cn.easemob.server.example;

import cn.easemob.server.example.comm.Constants;
import cn.easemob.server.example.comm.HTTPMethod;
import cn.easemob.server.example.comm.Roles;
import cn.easemob.server.example.httpclient.utils.HTTPClientUtils;
import cn.easemob.server.example.httpclient.vo.ClientSecretCredential;
import cn.easemob.server.example.httpclient.vo.Credential;
import cn.easemob.server.example.httpclient.vo.EndPoints;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2015/11/29 0029.
 */
public class SendMegHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(SendMegHelper.class);
    private static final String APPKEY = Constants.APPKEY;
    private static final JsonNodeFactory factory = new JsonNodeFactory(false);


    public static final String MSG_TYPE_SKILL_ORDER = "SKILL_ORDER";
    public static final String MSG_TYPE_STORE_ORDER = "STORE_ORDER";

    // 通过app的client_id和client_secret来获取app管理员token
    private static Credential credential = new ClientSecretCredential(Constants.APP_CLIENT_ID,
            Constants.APP_CLIENT_SECRET, Roles.USER_ROLE_APPADMIN);

    public static void main(String[] args) {

//        // 给用户发一条文本消息
        String from = "admin";
        String targetTypeus = "users";
        ObjectNode ext = factory.objectNode();
        ext.put("MSG_TYPE", "STORE_ORDER");
        ArrayNode targetusers = factory.arrayNode();
        targetusers.add("18381090832");
        ObjectNode txtmsg = factory.objectNode();
        txtmsg.put("msg", "Hello Easemob!");
        txtmsg.put("type", "txt");
        ObjectNode sendTxtMessageusernode = sendMessages(targetTypeus, targetusers, txtmsg, from, ext);
        if (null != sendTxtMessageusernode) {
            LOGGER.info("给用户发一条文本消息: " + sendTxtMessageusernode.toString());
        }
    }

    /**
     * 给用户发送消息
     * @param msgType
     */
    public static void sendMsg(String phoneNumber,String msgType,String msgContent){
        String from = "admin";
        String targetTypeus = "users";
        ObjectNode ext = factory.objectNode();
        ext.put("MSG_TYPE", msgType);
        ArrayNode targetusers = factory.arrayNode();
        targetusers.add(phoneNumber);
        ObjectNode txtmsg = factory.objectNode();
        txtmsg.put("msg", msgContent);
        txtmsg.put("type", "txt");
        ObjectNode sendTxtMessageusernode = sendMessages(targetTypeus, targetusers, txtmsg, from, ext);
        if (null != sendTxtMessageusernode) {
            LOGGER.info("给用户发一条文本消息: " + sendTxtMessageusernode.toString());
        }
    }

    /**
     * 发送消息
     *
     * @param targetType
     *            消息投递者类型：users 用户, chatgroups 群组
     * @param target
     *            接收者ID 必须是数组,数组元素为用户ID或者群组ID
     * @param msg
     *            消息内容
     * @param from
     *            发送者
     * @param ext
     *            扩展字段
     *
     * @return 请求响应
     */
    public static ObjectNode sendMessages(String targetType, ArrayNode target, ObjectNode msg, String from,
                                          ObjectNode ext) {

        ObjectNode objectNode = factory.objectNode();

        ObjectNode dataNode = factory.objectNode();

        // check appKey format
        if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
            LOGGER.error("Bad format of Appkey: " + APPKEY);

            objectNode.put("message", "Bad format of Appkey");

            return objectNode;
        }

        // check properties that must be provided
        if (!("users".equals(targetType) || "chatgroups".equals(targetType))) {
            LOGGER.error("TargetType must be users or chatgroups .");

            objectNode.put("message", "TargetType must be users or chatgroups .");

            return objectNode;
        }

        try {
            // 构造消息体
            dataNode.put("target_type", targetType);
            dataNode.put("target", target);
            dataNode.put("msg", msg);
            dataNode.put("from", from);
            dataNode.put("ext", ext);

            objectNode = HTTPClientUtils.sendHTTPRequest(EndPoints.MESSAGES_URL, credential, dataNode,
                    HTTPMethod.METHOD_POST);

            objectNode = (ObjectNode) objectNode.get("data");
            for (int i = 0; i < target.size(); i++) {
                String resultStr = objectNode.path(target.path(i).asText()).asText();
                if ("success".equals(resultStr)) {
                    LOGGER.error(String.format("Message has been send to user[%s] successfully .", target.path(i)
                            .asText()));
                } else if (!"success".equals(resultStr)) {
                    LOGGER.error(String.format("Message has been send to user[%s] failed .", target.path(i).asText()));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return objectNode;
    }
}
