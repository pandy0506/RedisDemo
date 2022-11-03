package com.myredsidemo.myrediscachedemo.utils

import java.util.*

class MyTimerTask(private val str:String) : TimerTask() {
    override fun run() {
        println(str)
    }
}
