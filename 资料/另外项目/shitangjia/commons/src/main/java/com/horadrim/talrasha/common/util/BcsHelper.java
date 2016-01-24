package com.horadrim.talrasha.common.util;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.ObjectMetadata;
import com.baidubce.services.bos.model.PutObjectResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.InputStream;

/**
 * Created by victor on 14-4-11.
 */
public class BcsHelper {
    private static final Log log = LogFactory.getLog(BcsHelper.class);
    // ----------------------------------------
    static String accessKey = "24437049526242ff90a4ed5ceca240bc";
    static String secretKey = "1daf7ac994824ae194fb622e43c77e9f";
    // ----------------------------------------

    public static final String STORAGE_URL_PREFIX = "http://qingcai-images.bj.bcebos.com/";

    private static BosClient client;

    static {
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(accessKey, secretKey));
        client = new BosClient(config);
    }

//    public static boolean putObject(String bucket, InputStream inputStream, String contentType, String path, String fileName, long size) {
//        ObjectMetadata objectMetadata = new ObjectMetadata();
//        objectMetadata.setContentType(contentType);
//        objectMetadata.setContentLength(size);
//        PutObjectRequest request = new PutObjectRequest(bucket, path + fileName, inputStream, objectMetadata);
//        request.setAcl(X_BS_ACL.PublicRead);
//        try {
//            BaiduBCSResponse<ObjectMetadata> response = baiduBCS.putObject(request);
//            ObjectMetadata result = response.getResult();
//            return true;
//        } catch (BCSClientException e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }

    public static boolean putObject(String bucket,String filename ,File file){
        try {

            PutObjectResponse response = client.putObject(bucket,filename,file);
            return response.getETag() != null;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

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

    public static boolean deleteObject(String bucket, String path, String filename) {
//        try {
//            Empty result = baiduBCS.deleteObject(bucket, path + filename).getResult();
//            return true;
//        } catch (BCSClientException e) {
//            e.printStackTrace();
//        }
//
        return false;
    }

//    public static void generateUrl(BaiduBCS baiduBCS) {
//        GenerateUrlRequest generateUrlRequest = new GenerateUrlRequest(HttpMethodName.GET, bucket, object);
//        generateUrlRequest.setBcsSignCondition(new BCSSignCondition());
//        generateUrlRequest.getBcsSignCondition().setIp("1.1.1.1");
//        generateUrlRequest.getBcsSignCondition().setTime(123455L);
//        generateUrlRequest.getBcsSignCondition().setSize(123455L);
//        System.out.println(baiduBCS.generateUrl(generateUrlRequest));
//    }
//
//    public static void copyObject(BaiduBCS baiduBCS, String destBucket, String destObject) {
//        ObjectMetadata objectMetadata = new ObjectMetadata();
//        objectMetadata.setContentType("image/jpeg");
//        baiduBCS.copyObject(new Resource(bucket, object), new Resource(destBucket, destObject), objectMetadata);
//        baiduBCS.copyObject(new Resource(bucket, object), new Resource(destBucket, destObject), null);
//        baiduBCS.copyObject(new Resource(bucket, object), new Resource(destBucket, destObject));
//    }
//
//    private static void createBucket(BaiduBCS baiduBCS) {
//        // baiduBCS.createBucket(bucket);
//        baiduBCS.createBucket(new CreateBucketRequest(bucket, X_BS_ACL.PublicRead));
//    }
//
//    private static void deleteBucket(BaiduBCS baiduBCS) {
//        baiduBCS.deleteBucket(bucket);
//    }
//
//    public static void deleteObject(BaiduBCS baiduBCS) {
//        Empty result = baiduBCS.deleteObject(bucket, object).getResult();
//        log.info(result);
//    }
//
//    private static void getBucketPolicy(BaiduBCS baiduBCS) {
//        BaiduBCSResponse<Policy> response = baiduBCS.getBucketPolicy(bucket);
//
//        log.info("After analyze: " + response.getResult().toJson());
//        log.info("Origianal str: " + response.getResult().getOriginalJsonStr());
//    }
//
//    public static void getObjectMetadata(BaiduBCS baiduBCS) {
//        ObjectMetadata objectMetadata = baiduBCS.getObjectMetadata(bucket, object).getResult();
//        log.info(objectMetadata);
//    }
//
//    private static void getObjectPolicy(BaiduBCS baiduBCS) {
//        BaiduBCSResponse<Policy> response = baiduBCS.getObjectPolicy(bucket, object);
//        log.info("After analyze: " + response.getResult().toJson());
//        log.info("Origianal str: " + response.getResult().getOriginalJsonStr());
//    }
//
//    private static void getObjectWithDestFile(BaiduBCS baiduBCS) {
//        GetObjectRequest getObjectRequest = new GetObjectRequest(bucket, object);
//        baiduBCS.getObject(getObjectRequest, destFile);
//    }
//
//    private static void listBucket(BaiduBCS baiduBCS) {
//        ListBucketRequest listBucketRequest = new ListBucketRequest();
//        BaiduBCSResponse<List<BucketSummary>> response = baiduBCS.listBucket(listBucketRequest);
//        for (BucketSummary bucket : response.getResult()) {
//            log.info(bucket);
//        }
//    }
//
//    private static void listObject(BaiduBCS baiduBCS) {
//        ListObjectRequest listObjectRequest = new ListObjectRequest(bucket);
//        listObjectRequest.setStart(0);
//        listObjectRequest.setLimit(20);
//        // ------------------by dir
//        {
//            // prefix must start with '/' and end with '/'
//            // listObjectRequest.setPrefix("/1/");
//            // listObjectRequest.setListModel(2);
//        }
//        // ------------------only object
//        {
//            // prefix must start with '/'
//            // listObjectRequest.setPrefix("/1/");
//        }
//        BaiduBCSResponse<ObjectListing> response = baiduBCS.listObject(listObjectRequest);
//        log.info("we get [" + response.getResult().getObjectSummaries().size() + "] object record.");
//        for (ObjectSummary os : response.getResult().getObjectSummaries()) {
//            log.info(os.toString());
//        }
//    }
//
//    private static void putBucketPolicyByPolicy(BaiduBCS baiduBCS) {
//        Policy policy = new Policy();
//        Statement st1 = new Statement();
//        st1.addAction(PolicyAction.all).addAction(PolicyAction.get_object);
//        st1.addUser("zhengkan").addUser("zhangyong01");
//        st1.addResource(bucket + "/111").addResource(bucket + "/111");
//        st1.setEffect(PolicyEffect.allow);
//        policy.addStatements(st1);
//        baiduBCS.putBucketPolicy(bucket, policy);
//    }
//
//    private static void putBucketPolicyByX_BS_ACL(BaiduBCS baiduBCS, X_BS_ACL acl) {
//        baiduBCS.putBucketPolicy(bucket, acl);
//    }
//
//    public static void putObjectByFile(BaiduBCS baiduBCS) {
//        PutObjectRequest request = new PutObjectRequest(bucket, object, createSampleFile());
//        ObjectMetadata metadata = new ObjectMetadata();
//        // metadata.setContentType("text/html");
//        request.setMetadata(metadata);
//        BaiduBCSResponse<ObjectMetadata> response = baiduBCS.putObject(request);
//        ObjectMetadata objectMetadata = response.getResult();
//        log.info("x-bs-request-id: " + response.getRequestId());
//        log.info(objectMetadata);
//    }
//
//    public static void putObjectByInputStream(BaiduBCS baiduBCS) throws FileNotFoundException {
//        File file = createSampleFile();
//        InputStream fileContent = new FileInputStream(file);
//        ObjectMetadata objectMetadata = new ObjectMetadata();
//        objectMetadata.setContentType("text/html");
//        objectMetadata.setContentLength(file.length());
//        PutObjectRequest request = new PutObjectRequest(bucket, object, fileContent, objectMetadata);
//        ObjectMetadata result = baiduBCS.putObject(request).getResult();
//        log.info(result);
//    }
//
//    private static void putObjectPolicyByPolicy(BaiduBCS baiduBCS) {
//        Policy policy = new Policy();
//        Statement st1 = new Statement();
//        st1.addAction(PolicyAction.all).addAction(PolicyAction.get_object);
//        st1.addUser("zhengkan").addUser("zhangyong01");
//        st1.addResource(bucket + object).addResource(bucket + object);
//        st1.setEffect(PolicyEffect.allow);
//        policy.addStatements(st1);
//        baiduBCS.putObjectPolicy(bucket, object, policy);
//    }
//
//    private static void putObjectPolicyByX_BS_ACL(BaiduBCS baiduBCS, X_BS_ACL acl) {
//        baiduBCS.putObjectPolicy(bucket, object, acl);
//    }
//
//    public static void putSuperfile(BaiduBCS baiduBCS) {
//        List<SuperfileSubObject> subObjectList = new ArrayList<SuperfileSubObject>();
//        // 0
//        BaiduBCSResponse<ObjectMetadata> response1 = baiduBCS.putObject(bucket, object + "_part0", createSampleFile());
//        subObjectList.add(new SuperfileSubObject(bucket, object + "_part0", response1.getResult().getETag()));
//        // 1
//        BaiduBCSResponse<ObjectMetadata> response2 = baiduBCS.putObject(bucket, object + "_part1", createSampleFile());
//        subObjectList.add(new SuperfileSubObject(bucket, object + "_part1", response2.getResult().getETag()));
//        // put superfile
//        PutSuperfileRequest request = new PutSuperfileRequest(bucket, object + "_superfile", subObjectList);
//        BaiduBCSResponse<ObjectMetadata> response = baiduBCS.putSuperfile(request);
//        ObjectMetadata objectMetadata = response.getResult();
//        log.info("x-bs-request-id: " + response.getRequestId());
//        log.info(objectMetadata);
//    }
//
//    public static void setObjectMetadata(BaiduBCS baiduBCS) {
//        ObjectMetadata objectMetadata = new ObjectMetadata();
//        objectMetadata.setContentType("text/html12");
//        baiduBCS.setObjectMetadata(bucket, object, objectMetadata);
//    }
//
//    private static File createSampleFile() {
//        try {
//            File file = File.createTempFile("java-sdk-", ".txt");
//            file.deleteOnExit();
//
//            Writer writer = new OutputStreamWriter(new FileOutputStream(file));
//            writer.write("01234567890123456789\n");
//            writer.write("01234567890123456789\n");
//            writer.write("01234567890123456789\n");
//            writer.write("01234567890123456789\n");
//            writer.write("01234567890123456789\n");
//            writer.close();
//
//            return file;
//        } catch (IOException e) {
//            log.error("tmp file create failed.");
//            return null;
//        }
//    }
}
