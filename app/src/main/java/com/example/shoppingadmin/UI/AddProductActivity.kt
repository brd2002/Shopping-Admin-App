package com.example.shoppingadmin.UI

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.shoppingadmin.PRODUCT_IMAGES_FOLDER_PATH
import com.example.shoppingadmin.R
import com.example.shoppingadmin.databinding.ActivityAddProductBinding
import com.example.shoppingadmin.products.models.Product


class AddProductActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAddProductBinding
    var product = Product()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.apply {
          addProductOnlyImage.setOnClickListener {
              pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
          }
        }
    }
    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        // Callback is invoked after the user selects a media item or closes the
        // photo picker.
        if (uri != null) {
            binding.showImage.setImageURI(uri)
            binding.showImage.visibility = View.VISIBLE
            Log.d("PhotoPicker", "Image is selected")
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

}