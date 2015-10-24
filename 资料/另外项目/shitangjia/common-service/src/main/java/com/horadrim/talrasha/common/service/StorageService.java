package com.horadrim.talrasha.common.service;

import java.io.File;
import java.io.InputStream;

/**
 * Created by Administrator on 2015/6/2.
 */
public interface StorageService {


    /**
     *可能根据菜品得到默认图片
     */
    String createDefaultMainUrlPath(int productType);

    String createFileUrlPath(String gwId, String filename);

    String createFileFullUrl(String path);

    String createFileUrlPrefix();
    /**
     * 根据文件名，删除指定文件
     * @param name 文件名
     * @return 删除成功返回true，否则返回false
     */
    boolean deleteFile(String name);

    /**
     * 上传文件到指定目录
     */
    String putFile(InputStream stream, String fileName,long size,String contentType) ;

    String putFile(File file, String fileName) ;

}
