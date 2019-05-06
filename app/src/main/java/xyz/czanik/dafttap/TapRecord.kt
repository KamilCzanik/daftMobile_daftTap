package xyz.czanik.dafttap

import java.lang.StringBuilder

data class TapRecord(val tapCount: Int,val attemptTime : Long) {
    fun toJsonString() = "{\"tapCount\":$tapCount,\"attemptTime\":$attemptTime}"
}