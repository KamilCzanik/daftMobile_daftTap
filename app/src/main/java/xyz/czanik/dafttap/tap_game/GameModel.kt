package xyz.czanik.dafttap.tap_game

import android.annotation.SuppressLint
import android.content.SharedPreferences
import xyz.czanik.dafttap.PrefsReader
import xyz.czanik.dafttap.TapGame.Companion.TAP_RANKING
import xyz.czanik.dafttap.TapRecord
import xyz.czanik.dafttap.toJsonString
import xyz.czanik.dafttap.topFive
import javax.inject.Inject

class GameModel @Inject constructor(private val prefs: SharedPreferences) : GameMVP.Model {

    override val ranking : ArrayList<TapRecord> = PrefsReader(prefs).getTapRanking()

    @SuppressLint("ApplySharedPref")
    override fun saveRanking() {
        prefs.edit().putString(TAP_RANKING,ranking.topFive().toJsonString()).commit()
    }
}