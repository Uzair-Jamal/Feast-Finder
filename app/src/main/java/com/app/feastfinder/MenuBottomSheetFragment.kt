package com.app.feastfinder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.app.feastfinder.databinding.FragmentMenuBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MenuBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentMenuBottomSheetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMenuBottomSheetBinding.inflate(inflater,container,false)
        val menuItem = listOf("Burger","Sandwich","Biryani","Nihari","Ice Cream","Burger","Sandwich","Biryani","Nihari","Ice Cream")
        val menuPrice = listOf("$5","$7","$17","$11","$2","$5","$7","$17","$11","$2")
        val menuImage = listOf(R.drawable.menu_photo,R.drawable.menu_photo_1,R.drawable.menu_photo_2,R.drawable.menu4,R.drawable.menu5)


        return binding.root
    }
}