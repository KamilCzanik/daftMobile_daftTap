package xyz.czanik.dafttap.rank

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager.getDefaultSharedPreferences
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.beust.klaxon.Klaxon
import kotlinx.android.synthetic.main.activity_main.*
import xyz.czanik.dafttap.R
import xyz.czanik.dafttap.TapGame
import xyz.czanik.dafttap.TapRecord
import xyz.czanik.dafttap.tap_game.GameActivity

class MainActivity : AppCompatActivity() {

    val prefs by lazy { getDefaultSharedPreferences(this) as SharedPreferences }
    val ranking by lazy { Klaxon().parseArray<TapRecord>(prefs.getString(TapGame.TAP_RANKING,"[]")!!)?.toCollection(ArrayList())!! }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureRecycler(TapRecordRecyclerViewAdapter(ranking))
        playButton.setOnClickListener { startActivity(Intent(this,GameActivity::class.java))}
    }

    private fun configureRecycler(recyclerAdapter: TapRecordRecyclerViewAdapter) {
        with(rankingRecyclerView) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = recyclerAdapter
            adapter?.notifyDataSetChanged()
        }
    }
}
