package com.example.alwaysremeber.functions.wordpage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alwaysremeber.R
import com.example.alwaysremeber.database.tables.Words
import kotlinx.android.synthetic.main.word_item_layout.view.*

class WordAdapter : RecyclerView.Adapter<WordAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val words = itemView.findViewById<TextView>(R.id.word_txt)

        fun bind(item : Words){
            words.text = item.words
        }

        companion object {
             fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.word_item_layout, parent, false)

                return ViewHolder(view)
            }
        }

    }

    var data = listOf<Words>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder.from(parent)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = data[position]
            holder.bind(item)
    }

    override fun getItemCount() = data.size


}