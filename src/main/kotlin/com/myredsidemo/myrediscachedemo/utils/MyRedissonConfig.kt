package com.myredsidemo.myrediscachedemo.utils

import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.IOException


@Configuration
class MyRedissonConfig {

    @Bean(destroyMethod = "shutdown") // 服务停止后调用 shutdown 方法。
    @Throws(IOException::class)
    fun redisson(): RedissonClient {
        // 1.创建配置
        val config = Config()
        // 集群模式
        // config.useClusterServers().addNodeAddress("127.0.0.1:7004", "127.0.0.1:7001");
        // 2.根据 Config 创建出 RedissonClient 示例。
        config.useSingleServer().setAddress("redis://127.0.0.1:6379")
        return Redisson.create(config)
    }
}