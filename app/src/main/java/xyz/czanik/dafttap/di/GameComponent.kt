package xyz.czanik.dafttap.di

import dagger.Component
import xyz.czanik.dafttap.tap_game.GameActivity

@Component(modules = [GameModule::class])
interface GameComponent {
    fun inject(gameActivity: GameActivity)
}