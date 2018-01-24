package com.liuyan.gateway.config;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
  
  
@Configuration  
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{
    @Bean(name="defaultKeyGenerator")
    public KeyGenerator defaultKeyGenerator()
    {  
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params)
                sb.append(obj.toString());
            return sb.toString();
        };
    }
    
    @Bean  
    public CacheManager cacheManager(RedisTemplate<String, String> redisTemplate) 
    {  
    	RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
    	//默认缓存失效时间配置12个小时
    	rcm.setDefaultExpiration(60 * 60 * 12L);
    	
    	//失效时间配置
    	Map<String, Long> expires = new HashMap<String, Long>();
    	rcm.setExpires(expires);
    	
        return rcm;
    }  
  
    /**
     * Value为Object时使用
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> objectRedisTemplate(RedisConnectionFactory factory)
    {
    	RedisSerializer<String> serializer = new StringRedisSerializer();
    	
    	RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
    	template.setConnectionFactory(factory);
    	template.setKeySerializer(serializer);
    	template.setHashKeySerializer(serializer);
    	template.afterPropertiesSet();
    	return template;
    }
    
    /**
     * Value为String时使用
     * @param factory
     * @return
     */
    @Bean  
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) 
    {  
        ObjectMapper om = new ObjectMapper();  
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);  
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);  
        
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);  
        jackson2JsonRedisSerializer.setObjectMapper(om); 
        
        StringRedisTemplate template = new StringRedisTemplate(factory);  
        template.setValueSerializer(jackson2JsonRedisSerializer);  
        template.afterPropertiesSet();
        return template;  
    }  
}  