package com.example.batikku.view.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.batikku.R
import com.example.batikku.databinding.ActivityRegisterBinding
import com.example.batikku.view.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.Signintv.setOnClickListener{
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        binding.signupButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val pass = binding.passwordEditText.text.toString()
            val repeat = binding.repeatpasswordEditText.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && repeat.isNotEmpty()) {
                if (pass == repeat) {

                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)

                    }else{
                        Toast.makeText(this,it.exception.toString() , Toast.LENGTH_SHORT).show()
                    }
                }

                }else{
                    Toast.makeText(this,"Password Tidak Sama", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Semua Input Harus Di Isi !", Toast.LENGTH_SHORT).show()
            }
        }
    }
}