package xyz.czanik.dafttap

import kotlin.math.max

fun Collection<TapRecord>.toJsonString() : String {
    val sb = StringBuilder().append("[")
    var prefix = ""
    forEach {
        sb.append(prefix).append(it.toJsonString())
        prefix = ","
    }
    return sb.append("]").toString()
}

fun Collection<TapRecord>.topFive() = sortedBy(TapRecord::tapCount).dropLast(max(size-5,0))