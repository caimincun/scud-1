package cn.scud.utils;

/**
 * Created by Administrator on 2015/6/29.
 */
public class BosClit {

    static String accessKey = "7f8c23f4e14e4b6183f7ef270585730c";
    static String secretKey = "e4e26d34623b44afaaa13614bbee2be2";
    // ----------------------------------------

    //图片访问的前缀
    public static final String STORAGE_URL_PREFIX = "http://scud-images.bj.bcebos.com";

    private static BosClient client;

    static {
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(accessKey, secretKey));
        client = new BosClient(config);
    }
}
