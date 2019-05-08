package xyz.czanik.dafttap

import kotlin.math.max

/** Serjalizacja do formatu JSON*/
fun Collection<TapRecord>.toJsonString() : String {
    val sb = StringBuilder().append("[")
    var prefix = ""
    forEach {
        sb.append(prefix).append(it.toJsonString())
        prefix = ","
    }
    return sb.append("]").toString()
}

fun Collection<TapRecord>.topFive() = sortedByDescending(TapRecord::tapCount).dropLast(max(size-5,0))