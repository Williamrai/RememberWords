package com.example.alwaysremeber.functions.mainpage

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alwaysremeber.R
import com.example.alwaysremeber.database.MainDatabase
import com.example.alwaysremeber.databinding.FragmentMainpageBinding
import com.example.alwaysremeber.functions.subcategorypage.SubCatViewModel
import com.example.alwaysremeber.functions.subcategorypage.SubCatViewModelFactory
import com.example.alwaysremeber.util.EqualSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_mainpage.*

class MainPageFragment : Fragment() {

    private val BOOKS = "Books"
    private val LANGUAGE = "Language"
    private val JUSTWORDS = "Just words"
    private val ALWAYSREM = "Always Remeber"

    private lateinit var viewModel: MainPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar?.title = ALWAYSREM

        val binding: FragmentMainpageBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_mainpage, container, false)

        //boolean variable for setting up the add button view
        var isAdded = false

        //setting up viewModel
        val application = requireNotNull(this.activity).application
        val dataSource = MainDatabase.getInstance(application).mainCategoryDao
        val viewModelFactory = MainPageViewModelFactory(dataSource = dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainPageViewModel::class.java)


        //setting up the adapter for recyclerView
        //clickListener that signals the observes to get ready for navigation
        val adapter = MainPageAdapter(MyListener { mainCategoryId ->
            //Toast.makeText(context, "$mainCategoryId", Toast.LENGTH_SHORT).show()
            viewModel.onClick(mainCategoryId)
        })

        //setting up the recyclerView
        val manager = GridLayoutManager(context, 2)
        binding.categoriesList.layoutManager = manager
        binding.categoriesList.adapter = adapter

        //itemDecoration for equal spacing the items in GridLayoutManager
        val spacing = resources.getDimensionPixelSize(R.dimen.sixteen) / 2
        binding.categoriesList.addItemDecoration(EqualSpacingItemDecoration(spacing))

        //observer for the mainCategory data
        //when it finds new data updates the list
        viewModel.mainCatDatas.observe(viewLifecycleOwner, Observer {
            Log.i("test", it.toString())
            adapter.submitList(it)
        })


        //observes the event data and triggers navigation when it receives the id from the adapter
        //click listeners
        viewModel.onClickEvent.observe(viewLifecycleOwner, Observer {
            it?.let {
                this.findNavController().navigate(
                    MainPageFragmentDirections.actionMainpageFragmentToCategoryPageFragment("", it)
                )
                viewModel.navigationFinished()
            }
        })

        //add button click listeners
        //adds main category to the db
        binding.addBtn.setOnClickListener {
            val bookName = binding.catBooksEdtext.text.toString()
            if (isAdded) {

                if (bookName.isEmpty()) {
                    Toast.makeText(activity,"You have not entered the name, try again",Toast.LENGTH_SHORT).show()
                    isAdded = false
                    binding.catBooksEdtext.visibility = View.GONE
                } else {//end if starts else
                    viewModel.insert(bookName).apply {
                        Log.i("apple", "successfully added")
                        binding.catBooksEdtext.visibility = View.GONE
                    }
                    isAdded = false
                    binding.catBooksEdtext.setText("")
                    binding.catBooksEdtext.hint = resources.getString(R.string.hint)
                }

            } else {//end if starts else
                binding.catBooksEdtext.visibility = View.VISIBLE
                isAdded = true

            }
        }


        //enables the option menu
        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu -> viewModel.clear()
        }
        return super.onOptionsItemSelected(item)
    }


}