package xyz.czanik.dafttap.tap_game

import android.annotation.SuppressLint
import android.content.SharedPreferences
import xyz.czanik.dafttap.PrefsReader
import xyz.czanik.dafttap.TapGame.Companion.TAP_RANKING
import xyz.czanik.dafttap.TapRecord
import xyz.czanik.dafttap.toJsonString
import xyz.czanik.dafttap.topFive
import javax.inject.Inject

/** Model spełniający rolę dostawcy danych */
class GameModel @Inject constructor(private val prefs: SharedPreferences) : GameMVP.Model {

    /**Ranking jest odczytywany oraz zapisywany w formacie tablicy JSON.
    zawsze zawiera nie więcej niż 5 wartości*/

    override val ranking : ArrayList<TapRecord> = PrefsReader(prefs).getTapRanking()

    @SuppressLint("ApplySharedPref")
    override fun saveRanking() {
        prefs.edit().putString(TAP_RANKING,ranking.topFive().toJsonString()).commit()
    }
}