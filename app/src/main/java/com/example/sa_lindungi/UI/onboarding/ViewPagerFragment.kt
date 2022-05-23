package com.example.sa_lindungi.UI.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sa_lindungi.R
import com.example.sa_lindungi.UI.onboarding.screens.FirstScreen
import com.example.sa_lindungi.UI.onboarding.screens.SecondScreen
import com.example.sa_lindungi.UI.onboarding.screens.ThirdScreen
import com.example.sa_lindungi.databinding.FragmentViewPagerBinding
import kotlinx.android.synthetic.main.fragment_view_pager.view.*

class ViewPagerFragment : Fragment() {
    private lateinit var binding : FragmentViewPagerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewPagerBinding.inflate(inflater, container, false)

        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)

        val fragmentlist = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter = ViewPagerAdapter(
            fragmentlist,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        view.viewPager.adapter = adapter

        return view
    }
}