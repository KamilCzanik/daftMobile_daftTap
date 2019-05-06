package xyz.czanik.dafttap.tap_game

import android.app.AlertDialog
import xyz.czanik.dafttap.TapRecord

interface TapGameMVP {

    interface View {
        fun display(alertDialog: AlertDialog)
    }

    interface Presenter {
        fun onTap()
    }

    interface Model {
        val ranking : ArrayList<TapRecord>

        fun saveRanking()
    }
}