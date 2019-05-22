package xyz.czanik.dafttap.tap_game

import android.content.res.Resources
import android.os.CountDownTimer
import xyz.czanik.dafttap.R
import xyz.czanik.dafttap.topFive
import java.lang.System.currentTimeMillis
import javax.inject.Inject

/** Presenter odpowiedzialny za logikę aplikacji oraz spojenie funkcjonalności View i Model'u*/
class GamePresenter @Inject constructor(
    override val view: GameMVP.View,
    override val model: GameMVP.Model,
    private val res: Resources) : GameMVP.Presenter {

    private var timeToStart = 4000L
    private val tapGame = TapGame(6000, currentTimeMillis())

    override fun onTap() { view.updateScore(++tapGame.score) }

    override fun prepareGame() { prepareTimer.start() }

    private val prepareTimer = object : CountDownTimer(timeToStart,1000) {

        override fun onTick(millisUntilFinished: Long) {
            timeToStart = millisUntilFinished
            view.showMessage((timeToStart/1000).toString())
        }

        override fun onFinish() {
            view.showMessage(res.getString(R.string.play))
            view.isTapViewEnabled = true
            gameTimer.start()
        }
    }

    private val gameTimer = object : CountDownTimer(tapGame.gameTime,1000) {

        override fun onTick(millisUntilFinished: Long) {
            tapGame.gameTime = millisUntilFinished
            view.updateTime((millisUntilFinished/1000))
        }

        override fun onFinish() {
            endGame()
            val record = tapGame.getRecord()

            model.ranking += record
            val message = if(model.ranking.topFive().contains(record)) {
                model.saveRanking()
                "${res.getString(R.string.new_record)} : ${record.tapCount}"
            } else
                res.getString(R.string.that_was_close)

                view.displayDialogWith(message,res.getString(R.string.game_finished))
        }
    }

    private fun endGame() {
        view.updateTime(0)
        view.isTapViewEnabled = false
    }
}