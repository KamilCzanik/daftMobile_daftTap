package xyz.czanik.dafttap

/** klasa POJO przedstawiająca pojedynczy wynik gry*/
data class TapRecord(val tapCount: Int,val attemptTime : Long) {
    fun toJsonString() = "{\"tapCount\":$tapCount,\"attemptTime\":$attemptTime}"
}