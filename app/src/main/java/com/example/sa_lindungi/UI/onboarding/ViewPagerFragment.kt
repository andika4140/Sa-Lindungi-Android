package com.example.sa_lindungi.UI.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.sa_lindungi.R
import com.example.sa_lindungi.UI.onboarding.screens.FirstScreen
import com.example.sa_lindungi.UI.onboarding.screens.SecondScreen
import com.example.sa_lindungi.UI.onboarding.screens.ThirdScreen
import com.example.sa_lindungi.databinding.FragmentThirdScreenBinding
import com.example.sa_lindungi.databinding.FragmentViewPagerBinding
import kotlinx.android.synthetic.main.fragment_view_pager.view.*

class ViewPagerFragment : Fragment() {
    private var _binding: FragmentViewPagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewPagerBinding.inflate(inflater, container, false)

        val fragmentLists = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter = ViewPagerAdapter(
            fragmentLists,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = adapter
        val view = binding.root
        return view
    }
}