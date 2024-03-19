package com.app.feastfinder.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.app.feastfinder.R
import com.app.feastfinder.adapter.CartMenuAdapter
import com.app.feastfinder.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private lateinit var binding:FragmentSearchBinding
    private lateinit var searchAdapter: CartMenuAdapter
    private val originalMenuItem = listOf("Burger","Sandwich","Biryani","Nihari",
        "Ice Cream","Biryani","Nihari","Ice Cream",
        "Biryani","Nihari","Ice Cream")
    private val originalMenuPrice = listOf("$5","$7","$17","$11","$2",
        "$5","$11","$2","$5","$11","$2","$5")
    private val originalMenuImage = listOf(R.drawable.menu_photo,R.drawable.menu_photo_1,R.drawable.menu_photo_2,
        R.drawable.menu4,R.drawable.menu5,R.drawable.menu_photo_2,R.drawable.menu4,R.drawable.menu5,
        R.drawable.menu_photo_2,R.drawable.menu4,R.drawable.menu5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private val filteredMenuFoodName = mutableListOf<String>()
    private val filteredMenuItemPrice = mutableListOf<String>()
    private val filteredMenuItemImage = mutableListOf<Int>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater,container,false)
        searchAdapter = CartMenuAdapter(filteredMenuFoodName,filteredMenuItemPrice,filteredMenuItemImage,requireContext())
        binding.searchRv.layoutManager = LinearLayoutManager(requireContext(),VERTICAL,false)
        binding.searchRv.adapter = searchAdapter

        setupSearchView()
        showAllMenu()
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showAllMenu() {
        filteredMenuFoodName.clear()
        filteredMenuItemPrice.clear()
        filteredMenuItemImage.clear()

        filteredMenuFoodName.addAll(originalMenuItem)
        filteredMenuItemPrice.addAll(originalMenuPrice)
        filteredMenuItemImage.addAll(originalMenuImage)

        searchAdapter.notifyDataSetChanged()
    }

    private fun setupSearchView() {
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                filterMenuItems(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterMenuItems(newText)
                return true
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun filterMenuItems(query: String?) {
        filteredMenuFoodName.clear()
        filteredMenuItemPrice.clear()
        filteredMenuItemImage.clear()

        originalMenuItem.forEachIndexed{ index, foodName ->
            if(foodName.contains(query.toString(),true)){
                filteredMenuFoodName.add(foodName)
                filteredMenuItemPrice.add(originalMenuPrice[index])
                filteredMenuItemImage.add(originalMenuImage[index])
            }
        }
        searchAdapter.notifyDataSetChanged()
    }

}