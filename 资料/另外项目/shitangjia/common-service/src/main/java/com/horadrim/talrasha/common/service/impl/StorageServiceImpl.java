package com.horadrim.talrasha.common.service.impl;

import com.horadrim.talrasha.common.service.StorageService;
import com.horadrim.talrasha.common.util.BcsHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;

/**
 * Created by Administrator on 2015/6/2.
 */
@Service("storageService")
public class StorageServiceImpl implements StorageService {

    static String bucket = "qingcai-images";

    public static final String FILE_URL_PREFIX = BcsHelper.STORAGE_URL_PREFIX + bucket;

    private static final String UPLOAD_DIR_NAME = "/upload/";
    private static final String DEFAULT_DIR_NAME = "/default/";

    @Override
    public String createDefaultMainUrlPath(int productType) {
        return null;
    }

    @Override
    public String createFileUrlPath(String gwId, String filename) {
        return null;
    }

    @Override
    public String createFileFullUrl(String path) {
        return FILE_URL_PREFIX + path;
    }

    @Override
    public String createFileUrlPrefix() {
        return FILE_URL_PREFIX;
    }

    @Override
    public boolean deleteFile(String name) {
//        if (name.startsWith("/default")) {
//            return true;
//        }
//
//        int index = name.lastIndexOf("/");
//        if (index > 0) {
//            String filename = name.substring(index);
//            String path = name.substring(0, index);
//            return BcsHelper.deleteObject(bucket, path, filename);
//        }

        return false;
    }

    @Override
    public String putFile(InputStream stream, String fileName,long size,String contentType) {
       // String path = UPLOAD_DIR_NAME ;
        if (BcsHelper.putObject(bucket,UPLOAD_DIR_NAME+fileName,contentType,size,stream)) {
            return UPLOAD_DIR_NAME + fileName;
        }

        return null;
    }

    @Override
    public String putFile(File file, String fileName) {
        if (BcsHelper.putObject(bucket,UPLOAD_DIR_NAME+fileName,file)) {
            return UPLOAD_DIR_NAME + fileName;
        }

        return null;
    }
}
