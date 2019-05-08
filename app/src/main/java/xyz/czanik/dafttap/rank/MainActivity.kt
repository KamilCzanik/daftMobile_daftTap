package xyz.czanik.dafttap.rank

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager.getDefaultSharedPreferences
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import xyz.czanik.dafttap.PrefsReader
import xyz.czanik.dafttap.R
import xyz.czanik.dafttap.tap_game.GameActivity

class MainActivity : AppCompatActivity() {

    private val prefs by lazy { getDefaultSharedPreferences(this) as SharedPreferences }
    private val ranking by lazy { PrefsReader(prefs).getTapRanking() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = getString(R.string.title)

        configureRecycler(TapRecordRecyclerViewAdapter(ranking))
        playButton.setOnClickListener {
            startActivity(Intent(this,GameActivity::class.java))
            finish()}
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
