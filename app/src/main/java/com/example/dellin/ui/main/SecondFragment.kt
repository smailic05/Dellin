package com.example.dellin.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.dellin.databinding.SecondFragmentBinding
import com.example.dellin.ui.main.adapters.ViewPagerFragmentAdapter
import com.google.android.material.tabs.TabLayoutMediator

class SecondFragment: Fragment() {
    private var _binding: SecondFragmentBinding? = null
    private val binding get() = _binding!!
    companion object {
        fun newInstance() = SecondFragment()
    }
    private val model: MainViewModel by activityViewModels()

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
        binding.viewPager.adapter= activity?.let { ViewPagerFragmentAdapter(it) }
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = "OBJECT ${(position + 1)}"
        }.attach()
//        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback()
//        {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//            }
//        }
//        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}