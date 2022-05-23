package com.example.sa_lindungi.UI.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sa_lindungi.R
import com.example.sa_lindungi.UI.scanAnimal.OptionActivity
import com.example.sa_lindungi.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        view.button_kenali_satwa.setOnClickListener {
            val intentToOption = Intent(context, OptionActivity::class.java)
            startActivity(intentToOption)
        }

        return view
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//    }
}