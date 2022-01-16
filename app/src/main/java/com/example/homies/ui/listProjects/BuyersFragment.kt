package com.example.homies.ui.listProjects

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homies.R
import com.example.homies.databinding.FragmentBuyersBinding
import com.example.homies.databinding.FragmentHomeBinding
import com.example.homies.room.BuyersAdapter

class BuyersFragment : Fragment() {

    private val myViewModel : ListViewModel by activityViewModels()
    private var _binding : FragmentBuyersBinding? = null
    private val binding get() = _binding!!
    lateinit var myAdapter : BuyersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBuyersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        myAdapter = BuyersAdapter()
        binding.rvBuyers.apply {
            layoutManager = LinearLayoutManager(this@BuyersFragment.context)
            adapter = myAdapter
        }

        myAdapter.onClick = {
            navController.navigate(BuyersFragmentDirections.actionBuyersFragmentToDetailsProjectFragment(it))
        }

        myViewModel.projectsBuyers.observe(viewLifecycleOwner, Observer {
            myAdapter.submitList(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}