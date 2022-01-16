package com.example.homies.header_footer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homies.R
import com.example.homies.databinding.FragmentFooterBinding
import com.example.homies.databinding.FragmentHeaderBinding

class FooterFragment : Fragment() {

    private lateinit var binding: FragmentFooterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFooterBinding.inflate(inflater, container, false)
        return binding.root
    }
}