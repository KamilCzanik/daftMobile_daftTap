package xyz.czanik.dafttap

class TapGame(var gameTime : Long, val gameStartTime: Long) {

    var score = 0

    fun getRecord() = TapRecord(score, gameStartTime)
}