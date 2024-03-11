package com.example.shoppingadmin.UI.sheets.product

import android.content.Intent
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shoppingadmin.R
import com.example.shoppingadmin.UI.AddProductActivity
import com.example.shoppingadmin.databinding.FragmentProductBinding

class ProductFragment : Fragment() {

   private lateinit var binding : FragmentProductBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
        binding.apply {
            addButton.setOnClickListener {
                var intent = Intent(this, AddProductActivity::class.java)
                startActivity(intent)
            }
        }
    }
}