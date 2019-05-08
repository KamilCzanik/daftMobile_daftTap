package xyz.czanik.dafttap.di

import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import xyz.czanik.dafttap.tap_game.GameActivity
import xyz.czanik.dafttap.tap_game.GameMVP
import xyz.czanik.dafttap.tap_game.GameModel
import xyz.czanik.dafttap.tap_game.GamePresenter

@Module
class GameModule(private val gameActivity: GameActivity) {

    @Provides
    fun providesView() : GameMVP.View = gameActivity

    @Provides
    fun providesPresenter(presenter: GamePresenter) : GameMVP.Presenter = presenter

    @Provides
    fun providesModel(model: GameModel) : GameMVP.Model = model

    @Provides
    fun providesSharedPrefs() : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(gameActivity)
}