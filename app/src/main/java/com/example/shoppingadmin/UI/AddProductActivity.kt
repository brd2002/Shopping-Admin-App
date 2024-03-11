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
import com.example.shoppingadmin.uploadImage

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

            uploadImage(PRODUCT_IMAGES_FOLDER_PATH, uri) { it, imageUrl ->
                if (it) {
                    product.displayImage = imageUrl
                    binding.showImage.setImageURI(uri)
                    //  product.productDisplayImages
                    binding.showImage.visibility = View.VISIBLE

                    Toast.makeText(this, "Image found Successfully!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Image Not found !", Toast.LENGTH_SHORT).show()

                }
            }

        } else {
            Toast.makeText(this, "Image Not found !", Toast.LENGTH_SHORT).show()
        }
    }
//    / Registers a photo picker activity launcher in multi-select mode.
    // In this example, the app lets the user select up to 5 media files.
    val pickMedia1 =
        registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(10)) { uris ->
            // Callback is invoked after the user selects media items or closes the
            // photo picker.
            if (uris.isNotEmpty()) {
                Log.d("PhotoPicker", "Number of items selected: ${uris.size}")
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }
}