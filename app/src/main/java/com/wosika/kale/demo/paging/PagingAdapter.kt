package com.wosika.kale.demo.paging

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wosika.kale.demo.R
import com.wosika.kale.demo.entity.FeedItem
import timber.log.Timber


private val mDiffCallback: DiffUtil.ItemCallback<FeedItem> =
    object : DiffUtil.ItemCallback<FeedItem>() {
        override fun areItemsTheSame(oldItem: FeedItem, newItem: FeedItem): Boolean {
            Timber.d("areItemsTheSame")
            return oldItem.id === newItem.id
        }

        override fun areContentsTheSame(oldItem: FeedItem, newItem: FeedItem): Boolean {
            Timber.d("areContentsTheSame")
            return oldItem == newItem
        }
    }

class PagingAdapter : PagedListAdapter<FeedItem, PagingAdapter.ViewHolder>(mDiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_paging, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val textView = holder.itemView as TextView
        val item = getItem(position)
        textView.text = item?.id
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}