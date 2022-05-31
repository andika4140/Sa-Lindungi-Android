package com.example.sa_lindungi.UI.onboarding.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.sa_lindungi.R
import com.example.sa_lindungi.databinding.FragmentSecondScreenBinding
import com.example.sa_lindungi.databinding.FragmentThirdScreenBinding
import kotlinx.android.synthetic.main.fragment_third_screen.view.*

class ThirdScreen : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_third_screen, container, false)

        view.button_finish.setOnClickListener{
            findNavController().navigate(R.id.action_viewPagerFragment_to_homeFragment2)
            onboardingFinished()
        }

        return view
    }

    private fun onboardingFinished(){
        val sharedPref = requireActivity().getSharedPreferences("onboarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }
}