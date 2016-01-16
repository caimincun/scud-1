package cn.easemob.server.example;

import cn.easemob.server.example.comm.Constants;
import cn.easemob.server.example.comm.HTTPMethod;
import cn.easemob.server.example.comm.Roles;
import cn.easemob.server.example.httpclient.utils.HTTPClientUtils;
import cn.easemob.server.example.httpclient.vo.ClientSecretCredential;
import cn.easemob.server.example.httpclient.vo.Credential;
import cn.easemob.server.example.httpclient.vo.EndPoints;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Created by Administrator on 2015/10/5.
 */
public class EasHealper {

    private static final JsonNodeFactory factory = new JsonNodeFactory(false);


    // 通过app的client_id和client_secret来获取app管理员token
    private static Credential credential = new ClientSecretCredential(Constants.APP_CLIENT_ID,
            Constants.APP_CLIENT_SECRET, Roles.USER_ROLE_APPADMIN);


    public static void main(String[] args) {
        /**
         * 注册IM用户[单个]
         */
        ObjectNode datanode = JsonNodeFactory.instance.objectNode();
        datanode.put("username", "18728120022");
        datanode.put("password", "123");
        ObjectNode createNewIMUserSingleNode = createNewIMUserSingle(datanode);
        if (null != createNewIMUserSingleNode) {
        }
    }

    /**
     * 环信用户注册
     *
     */
    public static void registerUser(String username,String password){
        ObjectNode datanode = JsonNodeFactory.instance.objectNode();
        datanode.put("username", username);
        datanode.put("password", password);
        ObjectNode createNewIMUserSingleNode = createNewIMUserSingle(datanode);
        if (null != createNewIMUserSingleNode) {
        }
    }


    /**
     * 注册IM用户[单个]
     *
     * 给指定Constants.APPKEY创建一个新的用户
     *
     * @param dataNode
     * @return
     */
    public static ObjectNode createNewIMUserSingle(ObjectNode dataNode) {

        ObjectNode objectNode = factory.objectNode();

        // check Constants.APPKEY format
        if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", Constants.APPKEY)) {
//            LOGGER.error("Bad format of Constants.APPKEY: " + Constants.APPKEY);

            objectNode.put("message", "Bad format of Constants.APPKEY");

            return objectNode;
        }

        objectNode.removeAll();

        // check properties that must be provided
        if (null != dataNode && !dataNode.has("username")) {
//            LOGGER.error("Property that named username must be provided .");

            objectNode.put("message", "Property that named username must be provided .");

            return objectNode;
        }
        if (null != dataNode && !dataNode.has("password")) {
//            LOGGER.error("Property that named password must be provided .");

            objectNode.put("message", "Property that named password must be provided .");

            return objectNode;
        }

        try {

            objectNode = HTTPClientUtils.sendHTTPRequest(EndPoints.USERS_URL, credential, dataNode,
                    HTTPMethod.METHOD_POST);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return objectNode;
    }

}
