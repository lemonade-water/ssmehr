//package com.neusoft.hr.business.common;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.data.redis.connection.RedisConnection;
//import org.springframework.data.redis.core.RedisCallback;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.RedisSerializer;
//import org.springframework.stereotype.Service;
//
//import java.util.concurrent.TimeUnit;
//
//@Service
//public class RedisServiceImp implements RedisService{
//
//    @Autowired
//    private RedisTemplate<String,?> redisTemplate;
//    @Override
//    public boolean set(String key, String value) {
//        //回调
//        return redisTemplate.execute(new RedisCallback<Boolean>() {
//            @Override
//            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
//                if(key!=null&&value!=null) {
//                    RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
//
//                    redisConnection.set(stringSerializer.serialize(key), stringSerializer.serialize(value));
//
//                    return true;
//                }else
//                    return false;
//            }
//        });
//    }
//
//    @Override
//    public String get(String key) {
//        return redisTemplate.execute(new RedisCallback<String>() {
//            @Override
//            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
//                RedisSerializer<String> stringRedisSerializer = redisTemplate.getStringSerializer();
//                byte[] bytes = redisConnection.get(stringRedisSerializer.serialize(key));
//                return stringRedisSerializer.deserialize(bytes);
//            }
//        });
//    }
//
//    @Override
//    public boolean expire(String key,long time) {
//        //return redisTemplate.expire(key,TimeUnit.SECONDS);
//        return  redisTemplate.expire(key,time,TimeUnit.SECONDS);
//    }
//
//    @SuppressWarnings("unchecked")  //用于抑制编译器产生警告信息。
//    public void del(String ... key){
//        if(key!=null&&key.length>0){
//            if(key.length==1){
//                redisTemplate.delete(key[0]);
//            }else{
//                for(int i=0;i<key.length;i++){
//                    redisTemplate.delete(key[i]);
//                }
//            }
//        }
//    }
//}
