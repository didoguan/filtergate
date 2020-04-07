package com.deepspc.filtergate.utils;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.util.List;

/**
 * 缓存工具类
 */
@Slf4j
public class CacheUtil {

	public static final String COMMON = "COMMON";

	public static final String CONSTANT = "CONSTANT";

	public static final String JWT = "JWT";

    private static final Object LOCKER = new Object();

    public static void put(String cacheName, Object key, Object value) {
        getOrAddCache(cacheName).put(new Element(key, value));
    }

    @SuppressWarnings("all")
    public static <T> T get(String cacheName, Object key) {
        Element element = getOrAddCache(cacheName).get(key);
        if (element == null) {
            return null;
        } else {
            Object objectValue = element.getObjectValue();
            return (T) objectValue;
        }
    }

    public static List getKeys(String cacheName) {
        return getOrAddCache(cacheName).getKeys();
    }

    public static void remove(String cacheName, Object key) {
        getOrAddCache(cacheName).remove(key);
    }

    public static void removeAll(String cacheName) {
        getOrAddCache(cacheName).removeAll();
    }

    private static Cache getOrAddCache(String cacheName) {
        CacheManager cacheManager = SpringContextHolder.getBean(CacheManager.class);
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            synchronized (LOCKER) {
                cache = cacheManager.getCache(cacheName);
                if (cache == null) {
                    cacheManager.addCacheIfAbsent(cacheName);
                    cache = cacheManager.getCache(cacheName);
                }
            }
        }
        return cache;
    }
}


