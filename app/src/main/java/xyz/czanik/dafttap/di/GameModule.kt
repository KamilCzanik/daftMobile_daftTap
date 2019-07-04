package xyz.czanik.dafttap.di

import android.content.SharedPreferences
import android.content.res.Resources
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import xyz.czanik.dafttap.tap_game.GameActivity
import xyz.czanik.dafttap.tap_game.GameContract
import xyz.czanik.dafttap.tap_game.GameRankingManager
import xyz.czanik.dafttap.tap_game.GameViewModel

@Module
class GameModule(private val gameActivity: GameActivity) {

    @Provides
    fun providesViewModel(presenter: GameViewModel) : GameContract.ViewModel = presenter

    @Provides
    fun providesGameRankingManager(model: GameRankingManager) : GameContract.RankingManager = model

    @Provides
    fun providesSharedPrefs() : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(gameActivity)

    @Provides
    fun providesResources() : Resources = gameActivity.resources
}