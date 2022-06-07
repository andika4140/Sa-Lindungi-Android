package com.example.sa_lindungi.UI.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.sa_lindungi.R
import com.example.sa_lindungi.databinding.ActivityDonationBinding
import com.example.sa_lindungi.databinding.FragmentFirstScreenBinding
import kotlinx.android.synthetic.main.fragment_first_screen.view.*
import kotlinx.android.synthetic.main.fragment_view_pager.*

class FirstScreen : Fragment() {
    private var _binding: FragmentFirstScreenBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstScreenBinding.inflate(inflater, container, false)

        //binding.name.text = viewModel.name
        binding.buttonNext1.setOnClickListener {
            val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
            viewPager?.currentItem = 1
        }

        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
//    private lateinit var binding : FragmentFirstScreenBinding
//    fun onCreate(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        //val binding = FragmentFirstScreenBinding.inflate(layoutInflater)
//        val binding = FragmentFirstScreenBinding.inflate(inflater, container, false)
//        //val view = inflater.inflate(R.layout.fragment_first_screen, container, false)
//
//        //val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
//
//        binding.buttonNext1.setOnClickListener{
//            val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
//            viewPager?.currentItem = 1
//        }
//        binding()
//    }
}