package xyz.czanik.dafttap.tap_game

import android.content.SharedPreferences
import com.beust.klaxon.Klaxon
import xyz.czanik.dafttap.TapRecord
import xyz.czanik.dafttap.toJsonString
import xyz.czanik.dafttap.topFive

class GameModel(private val prefs: SharedPreferences) : TapGameMVP.Model {

    override var time = 0L
    override var taps = 0

    override val ranking : ArrayList<TapRecord> =
        Klaxon().parseArray<TapRecord>(prefs.getString("tap_ranking","")!!)?.toCollection(ArrayList())!!

    override fun saveRanking() {
        prefs.edit().putString("ranking",ranking.topFive().toJsonString()).apply()
    }
}