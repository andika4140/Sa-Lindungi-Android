package com.example.sa_lindungi.UI.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.sa_lindungi.R
import com.example.sa_lindungi.UI.donation.DonationActivity
import com.example.sa_lindungi.UI.scanAnimal.OptionActivity
import com.example.sa_lindungi.databinding.FragmentFirstScreenBinding
import com.example.sa_lindungi.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        //binding.name.text = viewModel.name
        binding.buttonKenaliSatwa.setOnClickListener {
            val intentToOption = Intent(context, OptionActivity::class.java)
            startActivity(intentToOption)
        }

        binding.buttonDonasi.setOnClickListener {
            val intentToDonation = Intent(context, DonationActivity::class.java)
            startActivity(intentToDonation)
        }

        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_home, container, false)
//
//        view.button_kenali_satwa.setOnClickListener {
//            val intentToOption = Intent(context, OptionActivity::class.java)
//            startActivity(intentToOption)
//        }
//
//        view.button_donasi.setOnClickListener {
//            val intentToDonation = Intent(context, DonationActivity::class.java)
//            startActivity(intentToDonation)
//        }
//
//        return view
//    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//    }
}