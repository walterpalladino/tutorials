package com.whp.tutorials.config;


import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;


@Configuration
@EnableRedisRepositories(value = "com.whp.tutorials.repo")
public class RedisConfig {

  @Bean
  public LettuceConnectionFactory redisConnectionFactory() {
    RedisProperties properties = redisProperties();
    RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();

    configuration.setHostName(properties.getHost());
    configuration.setPort(properties.getPort());

    return new LettuceConnectionFactory(configuration);
  }

  @Bean
  public RedisTemplate<String, String> redisTemplate() {
    RedisTemplate<String, String> template = new RedisTemplate<String, String>();

    template.setConnectionFactory(redisConnectionFactory());

    return template;
  }

  @Bean
  @Primary
  public RedisProperties redisProperties() {
    return new RedisProperties();
  }
/*


  @Bean
  JedisConnectionFactory jedisConnectionFactory() {
    return new JedisConnectionFactory();
  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate() {
    final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
    template.setConnectionFactory(jedisConnectionFactory());
    template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
    return template;
  }

  @Bean
  public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
    return (builder) -> builder
        .withCacheConfiguration("cacheData",
            RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(1)));
  }
*/
}
