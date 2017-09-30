package newfeature;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created by robertpicyu on 2017/9/29.
 */
public class CacheDemo {
    public static void main(String[] args) {
        /**
         * Guava 中 cache的使用:
         * 1、caches 使用：
         *      get(k);
         *      set(k);
         *      invalide(k);
         * 2、cache 特性配置
         *      回收策略： size 、 time 、引用回收
         *      值刷新策略：
         *      值缺少时的load策略：
         *      添加、删除回调：
         */

        Cache<String, String> cache = CacheBuilder
                .newBuilder()
                /* 过期策略（主要！！） */
                .maximumSize(10000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                /* 刷新策略: 默认不设置 */
                //.refreshAfterWrite(10, TimeUnit.MILLISECONDS)
                .build(); // build(loader) ,可设置load方法

        String a = cache.getIfPresent("test");
        System.out.printf(a);
    }
}
