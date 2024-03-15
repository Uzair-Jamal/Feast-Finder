package com.app.feastfinder.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.app.feastfinder.R
import com.app.feastfinder.adapter.NotificationAdapter
import com.app.feastfinder.databinding.FragmentNotificationBottomBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NotificationBottomFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentNotificationBottomBinding
    private lateinit var notificationAdapter: NotificationAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding =  FragmentNotificationBottomBinding.inflate(inflater,container,false)
        val notification = listOf("Your order has been Canceled Successfully", "Order has been taken by the driver", "Congrats Your Order Placed")
        val notificationImg = listOf(R.drawable.sademoji, R.drawable.truck,R.drawable.illustration1)
        notificationAdapter = NotificationAdapter(ArrayList(notification),
            ArrayList(notificationImg))

        binding.notificationRv.layoutManager = LinearLayoutManager(requireContext(),VERTICAL,false)
        binding.notificationRv.adapter = notificationAdapter
        return binding.root
    }
}