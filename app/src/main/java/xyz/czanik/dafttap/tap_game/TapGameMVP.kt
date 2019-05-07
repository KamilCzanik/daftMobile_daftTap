package xyz.czanik.dafttap.tap_game

import xyz.czanik.dafttap.TapRecord

interface TapGameMVP {

    interface View {
        val presenter: Presenter
        var isTapViewEnabled: Boolean

        fun displayDialogWith(message: String,title: String)
        fun showMessage(message: String)
        fun updateScore(score: Int)
        fun updateTime(time: Long)
    }

    interface Presenter {
        val view: View
        val model: Model

        fun onTap()
        fun prepareGame()
    }

    interface Model {
        var time: Long
        var taps: Int
        val ranking : ArrayList<TapRecord>

        fun saveRanking()
    }
}