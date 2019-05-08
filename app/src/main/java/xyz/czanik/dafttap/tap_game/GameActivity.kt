package xyz.czanik.dafttap.tap_game

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_game.*
import xyz.czanik.dafttap.R
import xyz.czanik.dafttap.di.DaggerGameComponent
import xyz.czanik.dafttap.di.GameModule
import xyz.czanik.dafttap.rank.MainActivity
import javax.inject.Inject

class GameActivity : AppCompatActivity(),GameMVP.View {

    override var isTapViewEnabled: Boolean
        get() = rootLayout.isEnabled
        set(value) { rootLayout.isEnabled = value }

    @Inject override lateinit var presenter: GameMVP.Presenter

    override fun displayDialogWith(message: String,title: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.ok) { _,_ ->
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
            .setCancelable(false)
            .create().show()
    }

    override fun showMessage(message: String) {
        messageView.text = message
    }

    override fun updateScore(score: Int) {
        scoreView.text = score.toString()
    }

    @SuppressLint("SetTextI18n")
    override fun updateTime(time: Long) {
        timeLeftView.text = "$time sec"
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
