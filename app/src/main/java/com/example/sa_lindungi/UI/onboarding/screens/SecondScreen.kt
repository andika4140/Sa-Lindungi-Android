package com.example.sa_lindungi.UI.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.sa_lindungi.R
import com.example.sa_lindungi.databinding.FragmentFirstScreenBinding
import com.example.sa_lindungi.databinding.FragmentSecondScreenBinding
import kotlinx.android.synthetic.main.fragment_second_screen.view.*

class SecondScreen : Fragment() {
    private var _binding: FragmentSecondScreenBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondScreenBinding.inflate(inflater, container, false)

        //binding.name.text = viewModel.name
        binding.buttonNext2.setOnClickListener {
            val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
            viewPager?.currentItem = 2
        }

        val view = binding.root
        return view
    }
//    private lateinit var binding : FragmentSecondScreenBinding
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        val binding = FragmentSecondScreenBinding.inflate(inflater, container, false)
//        //val view = inflater.inflate(R.layout.fragment_second_screen, container, false)
//
//        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
//
//        binding.buttonNext2.setOnClickListener{
//            viewPager?.currentItem = 2
//        }
//        return view
//    }
}