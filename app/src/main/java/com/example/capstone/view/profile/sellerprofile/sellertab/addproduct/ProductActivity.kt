package com.example.capstone.view.profile.sellerprofile.sellertab.addproduct

import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.capstone.data.api.services.AddProduct
import com.example.capstone.databinding.ActivityAddProductBinding
import com.example.capstone.di.Injection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream


class ProductActivity : AppCompatActivity() {

    private var selectedPhotoUri: Uri? = null
    private lateinit var binding: ActivityAddProductBinding

    private val productViewModel: ProductViewModel by viewModels {
        ProductViewModelFactory(Injection.provideProductRepository(this))
    }

    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                selectedPhotoUri = it
                binding.imgPrudok.setImageURI(it)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTambahGambar.setOnClickListener {
            openPhotoPicker()
        }

        binding.btnTambahProduk.setOnClickListener {
            val name = binding.PutNamaBarang.text.toString()
            val description = binding.PutDescription.text.toString()
            val price = binding.PutPhone.text.toString().toFloatOrNull() ?: 0f
            val category = binding.PutJenisBarang.text.toString()

            if (validateInput(name, description, price, category)) {
                selectedPhotoUri?.let { uri ->
                    val file = getFileFromUri(uri)
                    file?.let {
                        uploadProductWithImage(name, description, price, category, it)
                    } ?: run {
                        Toast.makeText(this, "Failed to get file from URI", Toast.LENGTH_SHORT).show()
                    }
                } ?: run {
                    Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show()
                }
            }
        }


        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun openPhotoPicker() {
        pickMedia.launch("image/*")
    }

    private fun getFileFromUri(uri: Uri): File? {
        val contentResolver = applicationContext.contentResolver
        val fileName = getFileName(uri) ?: return null
        val tempFile = File(applicationContext.cacheDir, fileName)

        try {
            contentResolver.openInputStream(uri)?.use { input ->
                FileOutputStream(tempFile).use { output ->
                    input.copyTo(output)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

        return tempFile
    }

    private fun getFileName(uri: Uri): String? {
        var name: String? = null
        contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            if (cursor.moveToFirst()) {
                name = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
            }
        }
        return name
    }

    private fun uploadProductWithImage(name: String, description: String, price: Float, category: String, file: File) {
        productViewModel.uploadProduct(name, description, price, category, file)
    }

    private fun validateInput(name: String, description: String, price: Float, category: String): Boolean {
        if (name.isBlank() || description.isBlank() || price <= 0 || category.isBlank()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}