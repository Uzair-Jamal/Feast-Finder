package com.app.feastfinder

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.view.menu.MenuAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.app.feastfinder.Model.MenuItem
import com.app.feastfinder.adapter.CartMenuAdapter
import com.app.feastfinder.databinding.FragmentMenuBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MenuBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentMenuBottomSheetBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var menuItems: MutableList<MenuItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMenuBottomSheetBinding.inflate(inflater,container,false)
        retrieveMenuItems()


        binding.arrowImg.setOnClickListener {
            dismiss()
        }
        return binding.root
    }

    private fun retrieveMenuItems() {
        database = FirebaseDatabase.getInstance()
        val foodRef: DatabaseReference = database.reference.child("menu")
        menuItems = mutableListOf()

        foodRef.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(foodSnapshot in snapshot.children){
                    val menuItem = foodSnapshot.getValue(MenuItem::class.java)
                    menuItem?.let { menuItems.add(it)}
                }
                Log.d("ITEMS","onDataChange: Data received")
                setAdapter()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }

    private fun setAdapter() {
        if (menuItems.isNotEmpty()) {
            val menuAdapter = CartMenuAdapter(menuItems, requireContext())
            binding.menuRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), VERTICAL, false)
            binding.menuRecyclerView.adapter = menuAdapter
            Log.d("ITEMS", "set adapter: data set")
        }
        else{
            Log.d("ITEMS", "set adapter: data not set")
        }
    }
}