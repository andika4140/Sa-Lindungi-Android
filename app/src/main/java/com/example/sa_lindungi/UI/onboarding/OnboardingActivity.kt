package com.example.sa_lindungi.UI.onboarding

import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.sa_lindungi.R

class OnboardingActivity : AppCompatActivity() {
    private lateinit var onboard: ListOnboardItem
    private lateinit var binding: ActivityDetailOnboardBinding

    //private val vm: DetailStoryViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailOnboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            run {
                binding.tvDescription.justificationMode = JUSTIFICATION_MODE_INTER_WORD
            }
        }

        displayResult()
        setupToolbar()
    }

    private fun setupToolbar(){
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun displayResult() {
        with(binding){
            //tvName.text = vm.storyItem.name
            tvOnboardDesc.text = vm.storyItem.description

            Glide.with(ivOnboard)
                .load(vm.onboardItem.photoUrl) // URL Avatar
                .placeholder(R.drawable.ic_place_holder)
                .error(R.drawable.ic_broken_image)
                .into(ivOnboard)
        }
    }
}