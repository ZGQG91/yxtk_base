package com.yidu.jedis;

import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2016/12/6.
 */
public class RedisClient {
    public static Jedis jedis = null;

    public static Jedis getClient() {
        if (jedis == null) {
            jedis = new Jedis("192.168.4.180", 6379);
        }
        jedis.auth("readyidu");
        return jedis;
    }

    public static void main(String[]args){
        //System.out.println(RedisClient.getClient().set("key","123456"));
        System.out.println(RedisClient.getClient().get("1001"));
    }
}
