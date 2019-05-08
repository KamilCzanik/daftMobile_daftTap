package xyz.czanik.dafttap.tap_game

import android.content.res.Resources
import android.os.CountDownTimer
import xyz.czanik.dafttap.TapGame
import xyz.czanik.dafttap.topFive
import java.lang.System.currentTimeMillis

class GamePresenter(
    override val view: GameMVP.View,
    override val model: GameMVP.Model,
    private val resources: Resources) : GameMVP.Presenter {

    private val timeToStart = 3000L
    private val tapGame = TapGame(5000, currentTimeMillis())

    override fun onTap() { view.updateScore(++tapGame.score) }

    override fun prepareGame() { prepareTimer.start() }

    private val prepareTimer = object : CountDownTimer(timeToStart,1000) {

        override fun onFinish() {
            view.showMessage("play")
            view.isTapViewEnabled = true
            gameTimer.start()
        }

        override fun onTick(millisUntilFinished: Long) { view.showMessage(timeToStart.toString()) }
    }

    private val gameTimer = object : CountDownTimer(tapGame.gameTime,1000) {

        override fun onFinish() {
            view.isTapViewEnabled = false
            val record = tapGame.getRecord()
            model.ranking += record
            if(model.ranking.topFive().contains(record)) {
                model.saveRanking()
                view.displayDialogWith("You have set a new record with ${record.tapCount} taps","Game finished")
            } else
                view.displayDialogWith("That was close!!!","Game finished")

        }

        override fun onTick(millisUntilFinished: Long) { view.updateTime(millisUntilFinished) }
    }
}