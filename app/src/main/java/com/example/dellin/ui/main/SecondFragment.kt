package com.example.dellin.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.dellin.databinding.SecondFragmentBinding
import com.example.dellin.ui.main.adapters.ViewPagerFragmentAdapter
import com.google.android.material.tabs.TabLayoutMediator

class SecondFragment: Fragment(), AppBarInterface {
    private var _binding: SecondFragmentBinding? = null
    private val binding get() = _binding!!
    private val args: SecondFragmentArgs by navArgs()
    private var appBar: AppBarInterface?=null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        appBar=context as AppBarInterface
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SecondFragmentBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title="Выберите терминал"
        val currentTab:Int=args.chooseTab
        binding.viewPager.adapter=ViewPagerFragmentAdapter(requireActivity())
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when(position) {
                0 ->tab.text="откуда"
                1->tab.text="куда"
            }
        }.attach()
        binding.viewPager.setCurrentItem(currentTab,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appBar?.showAppBar()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun hideAppBar() {
    }

    override fun showAppBar() {
    }
}