package com.example.alwaysremeber.functions.subcategorypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.alwaysremeber.database.tables.SubCategory
import com.example.alwaysremeber.databinding.WordItemLayoutBinding

class BooksCatAdapter (val wordListener: WordListener) : ListAdapter<SubCategory, BooksCatAdapter.ViewHolder>(CatDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item,wordListener)
    }

    /**
     * View Holder for CatAdpater
     */
    class ViewHolder private constructor(val binding: WordItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        //binding.root is the root view that is the constraintLayout
        //uses data binding
        fun bind(item : SubCategory, wordListener: WordListener){
            binding.wordTxt.text = item.subCatName
            binding.subCategory = item
            binding.clickListener = wordListener
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                //data binding
                //links the single list item view
                val binding = WordItemLayoutBinding.inflate(layoutInflater,parent,false)

                //returns dataBinding
                return ViewHolder(binding)
            }
        }
    }
    /** End of ViewHolder */

}

//DiffUtil checks the differences between the list of datas
class CatDiffCallBack :DiffUtil.ItemCallback<SubCategory>(){
    override fun areItemsTheSame(oldItem: SubCategory, newItem: SubCategory): Boolean {
        return oldItem.sub_cat_id == newItem.sub_cat_id
    }

    override fun areContentsTheSame(oldItem: SubCategory, newItem: SubCategory): Boolean {
        return oldItem == newItem
    }

}

class WordListener(val clickListener : (word: String) -> Unit){
    fun onClick (subCategory: SubCategory) = clickListener(subCategory.subCatName!!)
}