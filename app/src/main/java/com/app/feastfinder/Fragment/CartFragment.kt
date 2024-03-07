package com.app.feastfinder.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.app.feastfinder.R
import com.app.feastfinder.adapter.CartAdapter
import com.app.feastfinder.databinding.FragmentCartBinding

class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater,container,false)

        val cartItem = listOf("Burger","Sandwich","Biryani","Nihari","Ice Cream")
        val cartPrice = listOf("$5","$7","$17","$11","$2")
        val cartImage = listOf(R.drawable.menu_photo,R.drawable.menu_photo_1,R.drawable.menu_photo_2,R.drawable.menu4,R.drawable.menu5)

        val adapter = CartAdapter(ArrayList(cartItem),ArrayList(cartImage),ArrayList(cartPrice))
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext(),VERTICAL,false)
        binding.cartRecyclerView.adapter = adapter
        return binding.root
    }

}