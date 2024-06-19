package com.example.batikku.view.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.example.batikku.R
import com.example.batikku.databinding.ActivityScanBinding
import com.example.batikku.view.Api.PredictApiConfig
import com.example.batikku.view.HashMap.BatikMapping
import com.example.batikku.view.Model.PredictResponse
import com.example.batikku.view.login.LoginActivity
import com.example.batikku.view.main.CameraActivity.Companion.CAMERAX_RESULT
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileNotFoundException

class ScanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScanBinding
    private lateinit var namabatik: TextView
    private lateinit var batikMapping: BatikMapping
    private var batikName: String? = null

    private var currentImageUri: Uri? = null

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(this, "Permission request granted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Permission request Approved", Toast.LENGTH_LONG).show()
            }
        }

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            this,
            REQUIRED_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        namabatik = findViewById(R.id.namaBatik)
        batikMapping = BatikMapping()

        if (!allPermissionsGranted()) {
            requestPermissionLauncher.launch(REQUIRED_PERMISSION)
        }

        binding.camButton.setOnClickListener { startCameraX() }
        binding.galButton.setOnClickListener { startGallery() }
        binding.predictButton.setOnClickListener { sendImageForPrediction() }
        val detButton: Button = findViewById(R.id.detButton)

        detButton.setOnClickListener {
            moveToDetailBatikByName()
        }
    }

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            showImage()
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    private fun startCameraX() {
        val intent = Intent(this, CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERAX_RESULT) {
            currentImageUri = it.data?.getStringExtra(CameraActivity.EXTRA_CAMERAX_IMAGE)?.toUri()
            showImage()
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.ivPreview.setImageURI(it)
        }
    }

    private fun sendImageForPrediction() {
        currentImageUri?.let { uri ->
            try {
                val inputStream = contentResolver.openInputStream(uri)
                    ?: throw FileNotFoundException("File not found")

                val tempFile = File.createTempFile("temp_image", ".jpg", cacheDir)
                tempFile.outputStream().use { outputStream ->
                    inputStream.copyTo(outputStream)
                }

                val requestFile = tempFile.asRequestBody("image/*".toMediaTypeOrNull())
                val imagePart = MultipartBody.Part.createFormData("file", tempFile.name, requestFile)

                PredictApiConfig.getPredictService().predictBatikName(imagePart)
                    .enqueue(object : Callback<PredictResponse> {
                        override fun onResponse(
                            call: Call<PredictResponse>,
                            response: Response<PredictResponse>
                        ) {
                            if (response.isSuccessful) {
                                Log.d("TAG", "onResponse: ${response.body()}")
                                val predictedBatikName = response.body()?.name ?: "Unknown"
                                Log.d("Predicted Batik", "predicted batik: $predictedBatikName")
                                batikName = predictedBatikName
                                namabatik.text = predictedBatikName
                                Toast.makeText(
                                    this@ScanActivity,
                                    "Predicted Batik: $predictedBatikName",
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                                Log.e("Prediction Error", "Failed to get predicted batik name")
                                response.errorBody()?.let { errorBody ->
                                    Log.e("Prediction Error", "Error Body: ${errorBody.string()}")
                                }
                            }
                        }

                        override fun onFailure(call: Call<PredictResponse>, t: Throwable) {
                            Log.e("Prediction Error", "Prediction request failed", t)
                        }
                    })
            } catch (e: Exception) {
                Log.e("Prediction Error", "Error sending image for prediction", e)
            }
        } ?: run {
            Toast.makeText(this@ScanActivity, "Please select an image first", Toast.LENGTH_SHORT).show()
        }
    }

    private fun moveToDetailBatikByName() {
        batikName?.let { name ->
            val batikId = batikMapping.getBatikId(name)
            if (batikId != null) {
                val intent = Intent(this, DetailActivity::class.java).apply {
                    putExtra("BATIK_ID", batikId)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Batik name not found in map. Please wait.", Toast.LENGTH_SHORT).show()
            }
        } ?: run {
            Toast.makeText(this, "Batik name not yet predicted. Please wait.", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val REQUIRED_PERMISSION = Manifest.permission.READ_EXTERNAL_STORAGE
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                performLogout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun performLogout() {
        // Contoh membersihkan shared preferences
        val sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()

        // Tampilkan pesan logout
        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()

        // Pindah ke LoginActivity
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
