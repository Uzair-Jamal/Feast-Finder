package com.app.feastfinder.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.app.feastfinder.Model.CartItems
import com.app.feastfinder.PayOut
import com.app.feastfinder.adapter.CartAdapter
import com.app.feastfinder.databinding.FragmentCartBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var foodNames: MutableList<String>
    private lateinit var foodPrices: MutableList<String>
    private lateinit var foodDescriptions: MutableList<String>
    private lateinit var foodIngredients: MutableList<String>
    private lateinit var foodImagesUri: MutableList<String>
    private lateinit var quantity: MutableList<Int>
    private lateinit var cartAdapter: CartAdapter
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater,container,false)
        auth = FirebaseAuth.getInstance()
        retrieveCartItems()


        binding.proceedBtn.setOnClickListener {
            startActivity(Intent(requireContext(),PayOut::class.java))
        }
        return binding.root
    }

    private fun retrieveCartItems() {
        database = FirebaseDatabase.getInstance()
        userId = auth.currentUser?.uid?:""
        val foodReference = database.reference.child("user").child(userId).child("cartItems")

        foodNames = mutableListOf()
        foodPrices = mutableListOf()
        foodDescriptions = mutableListOf()
        foodImagesUri = mutableListOf()
        foodIngredients = mutableListOf()
        quantity = mutableListOf()

        foodReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(dataSnapshots in snapshot.children){
                    val cartItems = dataSnapshots.getValue(CartItems::class.java)

                    cartItems?.foodName?.let { foodNames.add(it)}
                    cartItems?.foodPrice?.let { foodPrices.add(it)}
                    cartItems?.foodDescription?.let { foodDescriptions.add(it)}
                    cartItems?.foodImg?.let { foodImagesUri.add(it)}
                    cartItems?.foodQuantity?.let{ quantity.add(it)}
                    cartItems?.foodIngredients?.let {foodIngredients.add(it) }
                }

                cartAdapter = CartAdapter(requireContext(),foodNames,foodImagesUri,foodPrices,foodDescriptions,quantity,foodIngredients)
                binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext(),VERTICAL,false)
                binding.cartRecyclerView.adapter = cartAdapter

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context,"data not fetch",Toast.LENGTH_LONG).show()
            }

        })

    }

}