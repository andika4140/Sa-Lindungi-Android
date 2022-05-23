package com.example.sa_lindungi.UI.scanAnimal

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import com.example.sa_lindungi.UI.home.MainActivity
import com.example.sa_lindungi.UI.onboarding.HomeFragment
import com.example.sa_lindungi.databinding.ActivityResultBinding
import com.google.android.material.internal.ContextUtils.getActivity

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()

        binding.buttonHome.setOnClickListener { toHome() }
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun toHome() {
        val intentToMain = Intent(this, MainActivity::class.java)
        startActivity(intentToMain)
        finish()
    }
}