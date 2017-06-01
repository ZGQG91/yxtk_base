package com.yidu.compone.impl;

import com.yidu.Serializ.SerializableBean;
import com.yidu.cache.CacheClient;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/12/6.
 */
@Component
public class CacheCompone implements CacheClient{
    private RedisTemplate<String,Object> redisTemplate;
    private String name;
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    public void setName(String name) {
        this.name = name;
    }
    private byte[] toByteArray(Object obj) {
        Schema<SerializableBean> schema = RuntimeSchema.getSchema(SerializableBean.class);
        // 缓存buff
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        // 序列化成protobuf的二进制数据
        SerializableBean bean = new SerializableBean();
        bean.setBean(obj);
        return ProtobufIOUtil.toByteArray(bean, schema, buffer);
    }
    private <T> T toObject(byte[] bytes) {
        try {
            SerializableBean bean = new SerializableBean();
            Schema<SerializableBean> schema = RuntimeSchema.getSchema(SerializableBean.class);
            ProtobufIOUtil.mergeFrom(bytes, bean, schema);
            return (T) bean.getBean();
        } catch (Exception e) {
//            Tools.logger.error("serial Object exception", e);
        }
        return null;
    }
    private byte[] toStringByte(String key){
        return key.getBytes();
    }
    public void putObject(final String key, final Object object, final long expire) {
        try{
            redisTemplate.execute(new RedisCallback<Object>() {
                public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    redisConnection.set(toStringByte(key),toByteArray(object));
                    redisConnection.expire(toStringByte(key),expire);
                    return 0L;
                }
            });
        }catch(Exception e){
//            Tools.logger.debug(e.getMessage());
        }
    }
    public void set(final String key, final String str, final long expire) {
        try{
            redisTemplate.execute(new RedisCallback<Object>() {
                public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    redisConnection.set(toStringByte(key),toStringByte(str));
                    redisConnection.expire(toStringByte(key),expire);
                    return 0L;
                }
            });
        }catch(Exception e){
//            Tools.logger.error(e.getMessage());
        }
    }

    public boolean setNX(String key, String str, long exprie) {
        return false;
    }

    public <T> T getObject(final String key) {
        T obj=getObj(key);
        return obj;
    }

    public <T>T getObj(final String key){
        T obj=null;
        try {
            obj = redisTemplate.execute(new RedisCallback<T>() {
                public T doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    byte[] bytes=redisConnection.get(toStringByte(key));
                    T ob=toObject(bytes);
                    return ob;
                }
            });
        }catch(Exception e){
//            Tools.logger.error(e.getMessage());
        }
        return obj;
    }
    public <T>T getString(final String key) {
        T obj=getObj(key);
        return obj;
    }

    public Long remove(final String key) {
        try {
            redisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    return redisConnection.del(toStringByte(key));
                }
            });
        }catch(Exception e){
//            Tools.logger.error(e.getMessage());
        }
        return 0L;
    }

    public Long incr(final String key) {
        try{
            redisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    return redisConnection.incr(toStringByte(key));
                }
            });
        }catch(Exception e){
//            Tools.logger.error(e.getMessage());
        }
        return 0L;
    }

    public Long decr(final String key) {
        try{
            redisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    return redisConnection.decr(toStringByte(key));
                }
            });
        }catch(Exception e){
//            Tools.logger.error(e.getMessage());
        }
        return 0L;
    }
}
