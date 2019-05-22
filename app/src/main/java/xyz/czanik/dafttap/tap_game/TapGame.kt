package xyz.czanik.dafttap.tap_game

import xyz.czanik.dafttap.TapRecord

/** klasa POJO tworząca dodatkową warstę abstrakcji*/
class TapGame(private val gameStartTime: Long) {

    var score = 0
    val prepareTime = 4000L
    val gameTime = 6000L

    fun getRecord() = TapRecord(score, gameStartTime)

    companion object {
        const val TAP_RANKING = "tap_ranking"
    }
}