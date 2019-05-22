package xyz.czanik.dafttap.tap_game

import android.app.AlertDialog
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_game.*
import xyz.czanik.dafttap.R
import xyz.czanik.dafttap.di.DaggerGameComponent
import xyz.czanik.dafttap.di.GameModule
import xyz.czanik.dafttap.rank.MainActivity
import javax.inject.Inject

/** Aktywność gry
 * Implementuje funkcjonalność GameContract.View*/
class GameActivity : AppCompatActivity(),GameContract.View {

    @Inject override lateinit var viewModel: GameContract.ViewModel

    private var isTapViewEnabled: Boolean
        get() = rootLayout.isEnabled
        set(value) { rootLayout.isEnabled = value }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        DaggerGameComponent.builder().gameModule(GameModule(this)).build().inject(this)
        rootLayout.setOnClickListener { viewModel.onTap() }
        isTapViewEnabled = false
        viewModel.prepareGame()

        viewModel.scoreObservable.observe(this, Observer { score -> score?.let { scoreView.text = score.toString() } })
        viewModel.gameTimeObservable.observe(this, Observer {time -> time?.let { updateTime(time) } })
        viewModel.messageObservable.observe(this, Observer { message -> message?.let { messageView.text = message } })
        viewModel.endGameMessageObservable.observe(this, Observer { endMessage -> endMessage?.let { displayDialogWith(endMessage) } })
    }

    private fun updateTime(time: Long) {
        if(!isTapViewEnabled) isTapViewEnabled = true

        timeLeftView.text = "$time sec"
        progressBar.progress = time.toInt()
    }

    private fun displayDialogWith(message: String) {
        AlertDialog.Builder(this)
            .setTitle(R.string.game_finished)
            .setMessage(message)
            .setPositiveButton(R.string.ok) { _,_ ->
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
            .setCancelable(false)
            .create().show()
    }
}
