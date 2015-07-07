package cn.scud.utils;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.ObjectMetadata;
import com.baidubce.services.bos.model.PutObjectResponse;

import java.io.InputStream;

/**
 * Created by Administrator on 2015/6/29.
 * 图片上传到百度云BOS 存储
 */
public class BosHelper {

    public static String accessKey = "7f8c23f4e14e4b6183f7ef270585730c";
    public static String secretKey = "e4e26d34623b44afaaa13614bbee2be2";

    private static final String UPLOAD_DIR_NAME = "/upload/";

    public static String bucket = "scud-images";
    // ----------------------------------------

    //图片访问的前缀
    public static final String STORAGE_URL_PREFIX = "http://scud-images.bj.bcebos.com";

    private static BosClient client;

    static {
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(accessKey, secretKey));
        client = new BosClient(config);
    }

    public  boolean putObject(String bucket,String filename ,String contentType,long size,InputStream file){
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
     * 图片上传
     * @param stream
     * @param fileName
     * @param size
     * @param contentType
     * @return
     */
    public  String putFile(InputStream stream, String fileName,long size,String contentType) {
        // String path = UPLOAD_DIR_NAME ;
        if (putObject(bucket,UPLOAD_DIR_NAME+fileName,contentType,size,stream)) {
            return UPLOAD_DIR_NAME + fileName;
        }

        return null;
    }

    /**
     * 图片删除
     * @param objectKey
     */
    public void deleteObject(String objectKey) { //如：/upload/150701105336
        client.deleteObject(bucket,objectKey);           //指定要删除的Object所在Bucket名称和该Object名称
    }


}
