package com.corelib.net.request;

/**
 * Created by loyee on 16-1-5.
 */
public class CacheMode {
    public static final int CacheFirst = 1;//读取缓存，有缓存不请求网络，没有缓存请求网络
    public static final int CacheAndNet = 2;//先读取缓存，不管有没有缓存都再次请求网络
    public static final int Net = 3;//直接访问网络
}
