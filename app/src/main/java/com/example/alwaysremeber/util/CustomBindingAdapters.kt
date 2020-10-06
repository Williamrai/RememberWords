package com.example.alwaysremeber.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.alwaysremeber.database.tables.SubCategory
import com.example.alwaysremeber.functions.subcategorypage.BooksCatAdapter


@BindingAdapter("booksLists")
fun bindRecyclerViews(recyclerView: RecyclerView, data: List<SubCategory>?) {

    data?.let {
        val adapter = recyclerView.adapter as BooksCatAdapter
        adapter.submitList(data)
    }


}