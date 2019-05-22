package xyz.czanik.dafttap.tap_game

import xyz.czanik.dafttap.TapRecord

/** klasa POJO tworząca dodatkową warstę abstrakcji*/
class TapGame(var gameTime : Long, val gameStartTime: Long) {

    var score = 0

    fun getRecord() = TapRecord(score, gameStartTime)

    companion object {
        const val TAP_RANKING = "tap_ranking"
    }
}