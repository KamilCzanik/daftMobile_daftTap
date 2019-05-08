package xyz.czanik.dafttap.tap_game

import android.os.CountDownTimer
import xyz.czanik.dafttap.TapGame
import xyz.czanik.dafttap.topFive
import java.lang.System.currentTimeMillis
import javax.inject.Inject

class GamePresenter @Inject constructor(
    override val view: GameMVP.View,
    override val model: GameMVP.Model) : GameMVP.Presenter {

    private var timeToStart = 3000L
    private val tapGame = TapGame(5000, currentTimeMillis())

    override fun onTap() { view.updateScore(++tapGame.score) }

    override fun prepareGame() { prepareTimer.start() }

    private val prepareTimer = object : CountDownTimer(timeToStart,1000) {

        override fun onFinish() {
            view.showMessage("play")
            view.isTapViewEnabled = true
            gameTimer.start()
        }

        override fun onTick(millisUntilFinished: Long) {
            timeToStart = millisUntilFinished
            view.showMessage((timeToStart/1000).toString())
        }
    }

    private val gameTimer = object : CountDownTimer(tapGame.gameTime,1000) {

        override fun onFinish() {
            view.updateTime(0)
            view.isTapViewEnabled = false
            val record = tapGame.getRecord()
            model.ranking += record
            if(model.ranking.topFive().contains(record)) {
                model.saveRanking()
                view.showMessage("You have set a new record with ${record.tapCount} taps")
            } else
                view.showMessage("That was close!!!")

        }

        override fun onTick(millisUntilFinished: Long) {
            tapGame.gameTime = millisUntilFinished
            view.updateTime((millisUntilFinished/1000))
        }
    }
}