package com.example.dellin.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.dellin.MainViewModel
import com.example.dellin.databinding.SecondFragmentBinding
import com.example.dellin.ui.main.adapters.ViewPagerFragmentAdapter
import com.google.android.material.tabs.TabLayoutMediator

class SecondFragment: Fragment() {
    private var _binding: SecondFragmentBinding? = null
    private val binding get() = _binding!!
    private val args: SecondFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SecondFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentTab:Int=args.chooseTab
        binding.viewPager.adapter=ViewPagerFragmentAdapter(requireActivity())
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when(position) {
                0 ->tab.text="откуда"
                1->tab.text="куда"
            }
        }.attach()
        binding.viewPager.post{
            binding.viewPager.currentItem=currentTab/// Does not work without it
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}