package com.app.feastfinder

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.view.menu.MenuAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.app.feastfinder.adapter.CartMenuAdapter
import com.app.feastfinder.databinding.FragmentMenuBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MenuBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentMenuBottomSheetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMenuBottomSheetBinding.inflate(inflater,container,false)
        val menuItem = listOf("Burger","Sandwich","Biryani","Nihari","Ice Cream","Biryani","Nihari","Ice Cream","Biryani","Nihari","Ice Cream")
        val menuPrice = listOf("$5","$7","$17","$11","$2","$5","$11","$2","$5","$11","$2","$5")
        val menuImage = listOf(R.drawable.menu_photo,R.drawable.menu_photo_1,R.drawable.menu_photo_2,R.drawable.menu4,R.drawable.menu5,R.drawable.menu_photo_2,R.drawable.menu4,R.drawable.menu5,R.drawable.menu_photo_2,R.drawable.menu4,R.drawable.menu5)

        val menuAdapter = CartMenuAdapter(ArrayList(menuItem),ArrayList(menuPrice),ArrayList(menuImage),requireContext())
        binding.menuRecyclerView.layoutManager = LinearLayoutManager(requireContext(), VERTICAL,false)
        binding.menuRecyclerView.adapter = menuAdapter

        binding.arrowImg.setOnClickListener {
            dismiss()
        }
        return binding.root
    }
}