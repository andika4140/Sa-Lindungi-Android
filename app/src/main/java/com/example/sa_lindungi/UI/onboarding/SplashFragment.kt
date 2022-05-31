package com.example.sa_lindungi.UI.onboarding

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sa_lindungi.R

class SplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Handler().postDelayed({
            if (onboardingFinished()) {
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
            }
        }, 3001)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    private fun onboardingFinished(): Boolean{
        val sharedPref = requireActivity().getSharedPreferences("onboarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }
}