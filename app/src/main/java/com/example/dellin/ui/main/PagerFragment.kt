package com.example.dellin.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.dellin.databinding.RecyclerLayoutBinding
import com.example.dellin.ui.main.adapters.RecyclerAdapter


class PagerFragment : Fragment() {
    private var _binding: RecyclerLayoutBinding? = null
    private val binding get() = _binding!!
    private val model: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RecyclerLayoutBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recycler.adapter= RecyclerAdapter(model.arrayOfTerminals)
    }
}