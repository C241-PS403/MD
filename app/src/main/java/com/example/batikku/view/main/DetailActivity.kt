package com.example.batikku.view.main


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.batikku.R
import com.example.batikku.view.Model.ResponseBatikItem
import com.example.batikku.view.ViewModel.BatikDetailViewModel
import com.example.batikku.view.login.LoginActivity

class DetailActivity : AppCompatActivity() {

    private lateinit var batikDetailViewModel: BatikDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imageView: ImageView = findViewById(R.id.imageView7)
        val nameTextView: TextView = findViewById(R.id.textView_nama_batik)
        val originTextView: TextView = findViewById(R.id.textView_daerah_detail)
        val meaningTextView: TextView = findViewById(R.id.textView_makna_detail)
        val historyTextView: TextView = findViewById(R.id.textView_sejarah_detail)

        // Check for both possible extras
        val batikItem: ResponseBatikItem? = intent.getParcelableExtra("BATIK_ITEM")
        val batikId: Int = batikItem?.batikId ?: intent.getIntExtra("BATIK_ID", -1)

        if (batikId == -1) {
            // Handle missing batikId scenario
            Toast.makeText(this, "Batik ID is missing", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Setup ViewModel and observer to get batik detail data
        batikDetailViewModel = ViewModelProvider(this).get(BatikDetailViewModel::class.java)
        batikDetailViewModel.getBatikDetail(batikId)
        batikDetailViewModel.batikDetail.observe(this, Observer { batikDetail ->
            // Update UI with batik detail data (origin and meaning)
            if (batikDetail != null) {
                originTextView.text = batikDetail.batikOrigin
                meaningTextView.text = batikDetail.batikMeaning
                // Load image using Glide or any other method
                Glide.with(this)
                    .load(batikDetail.image)
                    .into(imageView)
                nameTextView.text = batikDetail.batikName
                historyTextView.text = batikDetail.batikHistory
            } else {
                // Handle the case where batikDetail is null
                Toast.makeText(this, "Failed to load batik details", Toast.LENGTH_SHORT).show()
            }
        })
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
