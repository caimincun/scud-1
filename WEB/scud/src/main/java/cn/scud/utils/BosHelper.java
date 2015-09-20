package cn.scud.utils;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.ObjectMetadata;
import com.baidubce.services.bos.model.PutObjectResponse;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Stack;

/**
 * Created by Administrator on 2015/6/29.
 * 图片上传到百度云BOS 存储
 */
public class BosHelper{

    public static String accessKey = "7f8c23f4e14e4b6183f7ef270585730c";
    public static String secretKey = "e4e26d34623b44afaaa13614bbee2be2";

    private static final String UPLOAD_DIR_NAME = "/upload/";

    // 用户头像的命名空间
    public static String userBucket = "scud-images";
    //技能相关图片命名空间
    public static String skillBucket = "scud-skills";
    //商店图片命名空间
    public static String storeBucket = "store-images";

    // ----------------------------------------

    //头像图片访问的前缀
    public static final String STORAGE_URL_PREFIX = "http://scud-images.bj.bcebos.com";
    // 技能相关图片访问前缀
    public static final String SKILL_URL_PREFIX ="http://scud-skills.bj.bcebos.com";
    // 商店图片访问前缀
    public static final String STORE_URL_PREFIX = "http://store-images.bj.bcebos.com";

    private static BosClient client;

    static {
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(accessKey, secretKey));
        client = new BosClient(config);
    }

    public static boolean putObject(String bucket,String filename ,String contentType,long size,InputStream file){
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(size);
            metadata.setContentType(contentType);
            PutObjectResponse response = client.putObject(bucket,filename,file,metadata);
            return response.getETag() != null;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    /**
     *  用户头像图片上传
     * @param stream
     * @param fileName
     * @param size
     * @param contentType
     * @return
     */
    public static String putUserImage(InputStream stream, String fileName,long size,String contentType) {
        // String path = UPLOAD_DIR_NAME ;
        if (putObject(userBucket,UPLOAD_DIR_NAME+fileName,contentType,size,stream)) {
            return UPLOAD_DIR_NAME + fileName;
        }

        return null;
    }

    /**
     * 上传技能相关图片
     * @param stream
     * @param fileName
     * @param size
     * @param contentType
     * @return
     */
    public static String putSkillImage(InputStream stream, String fileName,long size,String contentType) {
        // String path = UPLOAD_DIR_NAME ;
        if (putObject(skillBucket,UPLOAD_DIR_NAME+fileName,contentType,size,stream)) {
            return UPLOAD_DIR_NAME + fileName;
        }

        return null;
    }

    /**
     * 上传商店图片
     * @param stream
     * @param fileName
     * @param size
     * @param contentType
     * @return
     */
    public static String putStoreImage(InputStream stream, String fileName,long size,String contentType) {
        if (putObject(storeBucket,UPLOAD_DIR_NAME+fileName,contentType,size,stream)) {
            return UPLOAD_DIR_NAME + fileName;
        }

        return null;
    }



    /**
     * 用户头像图片删除
     * @param objectKey
     */
    public static void deleteUserObject(String objectKey) { //如：/upload/150701105336
        client.deleteObject(userBucket,objectKey);           //指定要删除的Object所在Bucket名称和该Object名称
    }

    /**
     * 删除 skill 相关图片
     * @param objectKey
     */
    public static void deleteSkillObject(String objectKey) { //如：/upload/150701105336
        client.deleteObject(skillBucket,objectKey);           //指定要删除的Object所在Bucket名称和该Object名称
    }
    /**
     * 删除 store 相关图片
     * @param objectKey
     */
    public static void deleteStoreObject(String objectKey) { //如：/upload/150701105336
        client.deleteObject(storeBucket,objectKey);           //指定要删除的Object所在Bucket名称和该Object名称
    }

}
