package xyz.czanik.dafttap.rank

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.tap_record_item.view.*
import xyz.czanik.dafttap.R
import xyz.czanik.dafttap.TapRecord

class TapRecordRecyclerViewAdapter(private val recordList: List<TapRecord> ) : RecyclerView.Adapter<TapRecordRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.tap_record_item,parent,false))
    }

    override fun getItemCount() = recordList.size

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        val record = recordList[pos]
        with(holder.itemView) {
            tapCountView.text = record.tapCount.toString()
            timestampView.text = record.attemptTime.toString()
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}