package xyz.czanik.dafttap.tap_game

import android.arch.lifecycle.MutableLiveData
import android.content.res.Resources
import android.os.CountDownTimer
import xyz.czanik.dafttap.R
import xyz.czanik.dafttap.topFive
import java.lang.System.currentTimeMillis
import javax.inject.Inject

/** ViewModel odpowiedzialny za logikę aplikacji oraz spojenie funkcjonalności View i RankingManager'u*/
class GameViewModel @Inject constructor(
    override val rankingManager: GameContract.RankingManager,
    private val res: Resources) : GameContract.ViewModel {

    override val gameTimeObservable: MutableLiveData<Long> = MutableLiveData()
    override val scoreObservable: MutableLiveData<Int> = MutableLiveData()
    override val endGameMessageObservable: MutableLiveData<String> = MutableLiveData()
    override val messageObservable: MutableLiveData<String> = MutableLiveData()

    private val tapGame = TapGame(currentTimeMillis())

    override fun prepareGame() { beforeGameTimer.start() }

    override fun onTap() { scoreObservable.value = ++tapGame.score }

    private val beforeGameTimer = object : CountDownTimer(tapGame.prepareTime,1000) {

        override fun onTick(millisLeft: Long) { messageObservable.value = (millisLeft/1000).toString() }

        override fun onFinish() {
            messageObservable.value = res.getString(R.string.play)
            gameTimer.start()
        }
    }

    private val gameTimer = object : CountDownTimer(tapGame.gameTime,1000) {

        override fun onTick(millisLeft: Long) { gameTimeObservable.value = (millisLeft/1000) }

        override fun onFinish() {
            gameTimeObservable.value = 0
            val record = tapGame.getRecord()

            rankingManager.ranking += record
            endGameMessageObservable.value = if(rankingManager.ranking.topFive().contains(record)) {
                rankingManager.saveRanking()
                "${res.getString(R.string.new_record)} : ${record.tapCount}"
            } else
                res.getString(R.string.that_was_close)
        }
    }
}