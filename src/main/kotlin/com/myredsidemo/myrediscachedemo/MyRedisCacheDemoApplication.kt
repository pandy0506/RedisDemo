package com.myredsidemo.myrediscachedemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class MyRedisCacheDemoApplication
hhhhh
fun main(args: Array<String>) {
    runApplication<MyRedisCacheDemoApplication>(*args)
}
