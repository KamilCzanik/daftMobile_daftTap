package xyz.czanik.dafttap.tap_game

import android.arch.lifecycle.MutableLiveData
import xyz.czanik.dafttap.TapRecord
/** Kontrakt MVVM definiujący obowiązki oraz zależności pomiędzy poszczególnymi elementami*/
interface GameContract {

    interface View {
        val viewModel: ViewModel
    }

    interface ViewModel {
        val rankingManager: RankingManager
        val gameTimeObservable: MutableLiveData<Long>
        val scoreObservable: MutableLiveData<Int>
        val endGameMessageObservable: MutableLiveData<String>
        val messageObservable: MutableLiveData<String>

        fun onTap()
        fun prepareGame()
    }

    interface RankingManager {
        val ranking : ArrayList<TapRecord>

        fun saveRanking()
    }
}