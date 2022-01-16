package com.example.homies.ui.listProjects

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homies.R
import com.example.homies.databinding.FragmentBuyersBinding
import com.example.homies.databinding.FragmentSalersBinding
import com.example.homies.room.BuyersAdapter
import com.example.homies.room.SalersAdapter

class SalersFragment : Fragment() {

    private val myViewModel : ListViewModel by activityViewModels()
    private var _binding : FragmentSalersBinding? = null
    private val binding get() = _binding!!
    lateinit var myAdapter : SalersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSalersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        myAdapter = SalersAdapter()
        binding.rvSalers.apply {
            layoutManager = LinearLayoutManager(this@SalersFragment.context)
            adapter = myAdapter
        }

        myAdapter.onClick = {
            navController.navigate(SalersFragmentDirections.actionSalersFragmentToDetailsProjectFragment(it))
        }

        myViewModel.projectSalers.observe(viewLifecycleOwner, Observer {
            myAdapter.submitList(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}