package com.example.sa_lindungi.UI.home

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.sa_lindungi.R
import com.example.sa_lindungi.UI.scanAnimal.OptionActivity
import com.example.sa_lindungi.databinding.ActivityHomeBinding
import com.example.sa_lindungi.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()

        binding.buttonKenaliSatwa.setOnClickListener {
            val intentToOption = Intent(this, OptionActivity::class.java)
            startActivity(intentToOption)
        }

        setContentView(binding.root)

        var navController =
            (supportFragmentManager.findFragmentById(R.id.activity_home_content) as NavHostFragment).navController
        navController.setGraph(R.navigation.main_host_navigation, intent.extras)

        binding.activityHomeNavigationView.setupWithNavController(navController)
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

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val mBinding = ActivityMainHostBinding.inflate(layoutInflater)
//        setContentView(mBinding.root)
//
//        navController = (supportFragmentManager.findFragmentById(R.id.activity_main_host_content) as NavHostFragment).navController
//        navController.setGraph(R.navigation.main_host_navigation, intent.extras)
//
//        mBinding.activityMainHostNavigationView.setupWithNavController(navController)
    }
}