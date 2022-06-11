package com.example.sa_lindungi.UI.onboarding.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.sa_lindungi.R
import com.example.sa_lindungi.databinding.FragmentFirstScreenBinding
import com.example.sa_lindungi.databinding.FragmentSecondScreenBinding
import com.example.sa_lindungi.databinding.FragmentThirdScreenBinding
import kotlinx.android.synthetic.main.fragment_third_screen.view.*

class ThirdScreen : Fragment() {
    private var _binding: FragmentThirdScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThirdScreenBinding.inflate(inflater, container, false)

        binding.buttonFinish.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_homeActivity)
            onboardingFinished()
        }

        val view = binding.root

        return view
    }

    private fun onboardingFinished(){
        val sharedPref = requireActivity().getSharedPreferences("onboarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }
}