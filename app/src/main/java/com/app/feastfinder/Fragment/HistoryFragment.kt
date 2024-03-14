package com.app.feastfinder.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.app.feastfinder.R
import com.app.feastfinder.adapter.BuyAgainAdapter
import com.app.feastfinder.databinding.FragmentHistoryBinding
class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var buyAgainAdapter: BuyAgainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(inflater,container,false)

        val buyAgainFoodName = arrayListOf("Food 2","Food 3", "Food 4","Food 5","Food 5")
        val buyAgainFoodPrice = arrayListOf("$10","$12","$30","$35","$35")
        val buyAgainFoodImage = arrayListOf(R.drawable.menu1,R.drawable.menu2,R.drawable.menu3,R.drawable.menu6,R.drawable.menu6)

        binding.ivHistory.setImageResource(R.drawable.menu6)
        buyAgainAdapter = BuyAgainAdapter(buyAgainFoodName,buyAgainFoodPrice,buyAgainFoodImage)
        binding.historyRv.layoutManager = LinearLayoutManager(requireContext(),VERTICAL,false)
        binding.historyRv.adapter = buyAgainAdapter

        return binding.root
    }

}