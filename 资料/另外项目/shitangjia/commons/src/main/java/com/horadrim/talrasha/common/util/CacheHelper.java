package com.horadrim.talrasha.common.util;

import com.baidu.bae.api.factory.BaeFactory;
import com.baidu.bae.api.memcache.BaeCache;

import java.util.Date;

/**
 * Created by Victor on 2014/5/4.
 */
public class CacheHelper {
    //cacheId为资源id，memcacheAddr为cache的服务地址和端口（例如，cache.duapp.com:10240）, user为ak, password为sk
    private static final String cacheId = "YMgRCKzDgKLtmSwucyZF";
    private static final String memcacheAddr = "cache.duapp.com:20243";
    private static final String username = "tzpILPobyGwyImmya1S0U7oo";
    private static final String password = "wq0k6smqjGYj6ga6KHBttEMNeWjPTVdH";

    private BaeCache baeCache;

    private static CacheHelper cacheHelper = null;

    private CacheHelper(){
        baeCache = BaeFactory.getBaeCache(cacheId, memcacheAddr, username, password);
    }

    /**
     * 获取实例对象
     *
     * @return
     */
    public static CacheHelper getCacheHelper() {
        if (cacheHelper == null) {
            cacheHelper = new CacheHelper();
        }

        return  cacheHelper;
    }

    /**
     * 从cache中取回key对应的值，如果key不存在则返回null
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        return baeCache.get(key);
    }

    /**
     * 将key-value存储在cache中，如果key已经存在，则返回false，新的value不会替换旧的value
     *
     * @param key 键
     * @param value 值
     * @return
     */
    public boolean add(String key, Object value) {
        return baeCache.add(key, value);
    }

    /**
     * 将key-value存储在cache中，如果key已经存在，则返回false，新的value不会替换旧的value
     *
     * @param key
     * @param value
     * @param expiry 有效期时间，单位毫秒
     * @return
     */
    public boolean add(String key, Object value, long expiry) {
        return baeCache.add(key, value, expiry);
    }

    /**
     * 将key-value存储在cache中，如果key已经存在，则返回false，新的value不会替换旧的value
     *
     * @param key
     * @param value
     * @param expiry 有效期时间，计算方法：用expiry - 当前毫秒数
     * @return
     */
    public boolean add(String key, Object value, Date expiry) {
        return baeCache.add(key, value, expiry);
    }

    /**
     * 给定key，从cache中将其对应的对象删除
     *
     * @param key
     * @return 删除成功返回true，否则返回false
     */
    public boolean del(String key) {
        return baeCache.delete(key);
    }

    /**
     * 给定key，从cache中延迟将其对应的对象删除
     *
     * @param key
     * @param expiry
     * @return
     */
    public boolean del(String key, long expiry) {
        return baeCache.delete(key, expiry);
    }

    /**
     * 给定key，从cache中延迟将其对应的对象删除
     *
     * @param key
     * @param expiry 有效期时间，计算方法：用expiry - 当前毫秒数
     * @return
     */
    public boolean del(String key, Date expiry) {
        return baeCache.delete(key, expiry);
    }

    /**
     * 更新cache中key所对应的value，如果key不存在则返回false
     *
     * @param key
     * @param newValue
     * @return
     */
    public boolean replace(String key, Object newValue) {
        return baeCache.replace(key, newValue);
    }

    /**
     * 更新cache中key所对应的value，如果key不存在则返回false
     *
     * @param key
     * @param newValue
     * @param expiry 效期时间，单位：毫秒
     * @return
     */
    public boolean replace(String key, Object newValue, long expiry) {
        return baeCache.replace(key, newValue, expiry);
    }

    /**
     * 更新cache中key所对应的value，如果key不存在则返回false
     *
     * @param key
     * @param newValue
     * @param expiry 有效期时间，计算方法：用expiry - 当前毫秒数
     * @return
     */
    public boolean replace(String key, Object newValue, Date expiry) {
        return baeCache.replace(key, newValue, expiry);
    }

    /**
     * 将key-value存储在cache中，如果key已经存在，则旧的value将会被当前value所替代
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value) {
        return baeCache.set(key, value);
    }

    /**
     * 将key-value存储在cache中，如果key已经存在，则旧的value将会被当前value所替代
     *
     * @param key 存储的key
     * @param value 存储的值
     * @param expiry 有效期时间，计算方法：用expiry - 当前毫秒数
     * @return
     */
    public boolean set(String key, Object value, long expiry) {
        return baeCache.set(key, value, expiry);
    }

    /**
     * 将key-value存储在cache中，如果key已经存在，则旧的value将会被当前value所替代
     *
     * @param key
     * @param value
     * @param expiry 有效期时间，计算方法：用expiry - 当前毫秒数
     * @return
     */
    public boolean set(String key, Object value, Date expiry) {
        return baeCache.set(key, value, expiry);
    }

    /**
     * 检查给定的key是否存在
     *
     * @param key
     * @return
     */
    public boolean exist(String key) {
        return baeCache.keyExists(key);
    }
}
