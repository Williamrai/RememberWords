package com.example.alwaysremeber.functions.subcategorypage

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.alwaysremeber.MainActivity
import com.example.alwaysremeber.R
import com.example.alwaysremeber.database.MainDatabase
import com.example.alwaysremeber.database.tables.SubCategory
import com.example.alwaysremeber.databinding.FragmentCategoryPageBinding
import com.example.alwaysremeber.functions.search.SearchPromptFragment
import com.example.alwaysremeber.functions.search.SearchViewModel
import com.google.android.material.snackbar.Snackbar


class SubCategoryPageFragment : Fragment() {

    private lateinit var categoryViewModelSub: SubCatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.i("test", "onCreateView Created")
        val binding: FragmentCategoryPageBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_category_page, container, false)
        // Inflate the layout for this fragment

        val args = SubCategoryPageFragmentArgs.fromBundle(requireArguments())

        val application = requireNotNull(this.activity).application
        val dataSource = MainDatabase.getInstance(application).subCategoryDao

        val viewModelFactory = SubCatViewModelFactory(args.idMainCategory, dataSource)
        categoryViewModelSub =
            ViewModelProvider(this, viewModelFactory).get(SubCatViewModel::class.java)
        var isClickedOkayButton: Boolean = false

        binding.catViewModel = categoryViewModelSub
        binding.lifecycleOwner = viewLifecycleOwner


        val adapter = BooksCatAdapter(WordListener { word ->
            categoryViewModelSub.updateWord(word)
        })
        binding.recyclerView.adapter = adapter


        //observes and updates the list whenever data is added to database
//        categoryViewModelBooks.categoriesList.observe(viewLifecycleOwner, Observer {
//            it?.let {
//                adapter.submitList(it)
//            }
//        })

        //observes the item on the list by recyclerview
        //and scrolls to position 0 when data is added
        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                binding.recyclerView.scrollToPosition(0)
            }
        })


        //observes the dialog okay button click
        //and sets the variable to be true when clicked
        //when the insertion finished the variable is set to false
        //which saves from running the insertion function again and again
        //as the viewModel of dialog fragment is still running and has the values
        //as onViewCreate is called once when the fragment starts and at that time because the dialog fragment viewModel
        //is still alive the data is added again to database
        categoryViewModelSub.successfullInsertion.observe(viewLifecycleOwner, Observer {
            if (it!!) {
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    "Loading....",
                    Snackbar.LENGTH_SHORT
                ).show()
                categoryViewModelSub.doneInsertion()
            }
        })

        //clickListener for the floating button
        //enables the function for adding data
        //starts the dialog fragment
        binding.addFloatingBtn.setOnClickListener {
            isClickedOkayButton = true
            SearchPromptFragment.newInstance()
                .show((context as MainActivity).supportFragmentManager, "searchPromptFragment")

        }


        //this updates the data or adds data to the list
        //this use share view model with this fragment and dialog fragment
        //there is a boolean that enables or disables the insertion
        //once insertion is done it disables the function
        val viewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)
        viewModel.bookName.observe(viewLifecycleOwner, { word ->
            if (isClickedOkayButton!!) {
                categoryViewModelSub.onSearch(args.idMainCategory, word)
            }
        })

        categoryViewModelSub.watchForClickListener.observe(viewLifecycleOwner, { word ->
            word?.let {
                this.findNavController().navigate(
                    SubCategoryPageFragmentDirections.actionCategoryPageFragmentToBrowserFragment(it)

                )
                categoryViewModelSub.doneWatchingForClickListener()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu) {
            categoryViewModelSub.clear()
        }
        return super.onOptionsItemSelected(item)
    }
}

