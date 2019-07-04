package xyz.czanik.dafttap

import android.content.SharedPreferences
import com.beust.klaxon.Klaxon
import xyz.czanik.dafttap.tap_game.TapGame

class PrefsReader(val prefs: SharedPreferences) {

    fun getTapRanking() = Klaxon().parseArray<TapRecord>(prefs.getString(TapGame.TAP_RANKING,"[]")!!)?.toCollection(ArrayList())!!

}