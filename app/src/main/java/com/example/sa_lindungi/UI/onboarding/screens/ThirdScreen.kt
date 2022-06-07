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
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThirdScreenBinding.inflate(inflater, container, false)

        //binding.name.text = viewModel.name
        binding.buttonFinish.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_homeActivity)
            onboardingFinished()
        }

        val view = binding.root

        val extras = ActivityNavigator.Extras.Builder()
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            .build()

        val navOption = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .build()

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
//    private lateinit var binding : FragmentThirdScreenBinding
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        //val binding = FragmentThirdScreenBinding.inflate(inflater, container, false)
//        val view = inflater.inflate(R.layout.fragment_third_screen, container, false)
//
//        val extras = ActivityNavigator.Extras.Builder()
//            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            .build()
//        val navOption = NavOptions.Builder()
//            .setLaunchSingleTop(true)
//            .build()
//
//        view.button_finish.setOnClickListener{
//            findNavController().navigate(R.id.action_viewPagerFragment_to_homeActivity)
//            onboardingFinished()
//        }
//        return view
//    }

    private fun onboardingFinished(){
        val sharedPref = requireActivity().getSharedPreferences("onboarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }
}