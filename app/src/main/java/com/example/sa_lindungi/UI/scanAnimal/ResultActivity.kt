package com.example.sa_lindungi.UI.scanAnimal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
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

    private fun proceedToHome(user: User?){
        if(user != null && user.id != 0L){
            val direction = LoginFragmentDirections.actionLoginToMainHostActivity(user.id)
            val extras = ActivityNavigator.Extras.Builder()
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .build()
            val navOptions = NavOptions.Builder()
                .setLaunchSingleTop(true)
                .build()
            findNavController().navigate(direction.actionId, direction.arguments, navOptions, extras)
        }
    }
}