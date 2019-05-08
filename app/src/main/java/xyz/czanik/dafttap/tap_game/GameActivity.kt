package xyz.czanik.dafttap.tap_game

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game.*
import xyz.czanik.dafttap.R
import xyz.czanik.dafttap.di.DaggerGameComponent
import xyz.czanik.dafttap.di.GameModule
import javax.inject.Inject

class GameActivity : AppCompatActivity(),GameMVP.View {

    override var isTapViewEnabled: Boolean
        get() = rootLayout.isEnabled
        set(value) { rootLayout.isEnabled = value }

    @Inject override lateinit var presenter: GameMVP.Presenter

    override fun displayDialogWith(message: String,title: String) {
        AlertDialog.Builder(applicationContext)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.ok) { dialog,i -> finish()}
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
    }

    override fun updateScore(score: Int) {
        scoreView.text = score.toString()
    }

    override fun updateTime(time: Long) {
        timeLeftView.text = time.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        DaggerGameComponent.builder().gameModule(GameModule(this)).build().inject(this)
        rootLayout.setOnClickListener { presenter.onTap() }
        isTapViewEnabled = false
        presenter.prepareGame()
    }
}
