package xyz.czanik.dafttap.tap_game

import android.content.res.Resources
import android.os.CountDownTimer
import xyz.czanik.dafttap.R
import xyz.czanik.dafttap.TapRecord
import xyz.czanik.dafttap.topFive

class GamePresenter(
    override val view: TapGameMVP.View,
    override val model: TapGameMVP.Model,
    private val resources: Resources) : TapGameMVP.Presenter {

    private var timeToStart = 3000L
    private var timeToFinish = 5000L

    override fun prepareGame() {
        //start 3sec timer
        countDownToStartTimer.start()
        //enable tapView for 5 sec
        //if timer stops disbale tap view and display result
    }

    override fun onTap() {

    }

    private val countDownToStartTimer = object : CountDownTimer(timeToStart,1000) {
        override fun onFinish() {
            view.showMessage("PLAY !!!")
            view.isTapViewEnabled = true
            model.time = System.currentTimeMillis()
            countDownToFinishTimer.start()
        }

        override fun onTick(millisUntilFinished: Long) {
            timeToStart = millisUntilFinished
            view.showMessage((timeToStart/1000).toString())
        }

    }

    private val countDownToFinishTimer = object : CountDownTimer(timeToFinish,1000) {

        override fun onFinish() {
            view.isTapViewEnabled = false
            val record = TapRecord(model.taps,model.time)
            model.ranking += record
            if(model.ranking.topFive().contains(record)) {
                model.saveRanking()
                view.displayDialogWith(resources.getString(R.string.total_taps) + record.tapCount.toString(),
                    resources.getString(R.string.new_record))
            }
            else
                view.displayDialogWith(resources.getString(R.string.total_taps) + record.tapCount.toString(),
                    resources.getString(R.string.that_was_close))
        }

        override fun onTick(millisUntilFinished: Long) {
            timeToStart = millisUntilFinished
            view.updateTime(timeToStart)
        }

    }
}