package com.example.alwaysremeber.functions.mainpage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.alwaysremeber.R
import com.example.alwaysremeber.database.tables.MainCategory
import com.example.alwaysremeber.databinding.MainCategoryOneItemListBinding

class MainPageAdapter (val clickListener : MyListener): ListAdapter<MainCategory, MainPageAdapter.MainPageViewHolder>(MainDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainPageViewHolder {
        return MainPageViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MainPageViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item!!, clickListener)
    }

    class MainPageViewHolder private constructor(val binding : MainCategoryOneItemListBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: MainCategory, clickListener: MyListener){
            binding.categoryBtn.background = binding.root.resources.getDrawable(R.drawable.img_books)
            binding.categoryNameTxt.text = item.mainCatName
            binding.mainCategory = item
            binding.clickListener = clickListener
        }

        companion object {
             fun from(parent: ViewGroup): MainPageViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MainCategoryOneItemListBinding.inflate(layoutInflater,parent,false)

                return MainPageViewHolder(binding)
            }
        }
    }




}

class MainDiffCallback : DiffUtil.ItemCallback<MainCategory>(){
    override fun areItemsTheSame(oldItem: MainCategory, newItem: MainCategory): Boolean {
        return oldItem.main_cat_id == newItem.main_cat_id
    }

    override fun areContentsTheSame(oldItem: MainCategory, newItem: MainCategory): Boolean {
        return oldItem == newItem
    }

}

class MyListener(val clickListener : (id : Long) -> Unit){
    fun onClick(mainCategory: MainCategory) = clickListener(mainCategory.main_cat_id)
}