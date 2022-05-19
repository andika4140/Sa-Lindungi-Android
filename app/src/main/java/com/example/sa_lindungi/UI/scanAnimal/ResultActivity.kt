package com.example.sa_lindungi.UI.scanAnimal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sa_lindungi.UI.home.MainActivity
import com.example.sa_lindungi.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonHome.setOnClickListener { toHome() }
    }

    private fun toHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}