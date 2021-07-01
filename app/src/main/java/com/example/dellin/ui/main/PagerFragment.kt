package com.example.dellin.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.dellin.MainViewModel
import com.example.dellin.TerminalsParsed
import com.example.dellin.databinding.RecyclerLayoutBinding
import com.example.dellin.databinding.SecondFragmentBinding
import com.example.dellin.ui.main.adapters.RecyclerAdapter



class PagerFragment(private val position: Int) : Fragment() {
    private var _binding: RecyclerLayoutBinding? = null
    private val binding get() = _binding!!
    private val model: MainViewModel by activityViewModels()
    val args: SecondFragmentArgs by navArgs()
    private val list= mutableListOf<TerminalsParsed?>()
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
        val array=model.array
        when(position){
            0-> {
                if (array!=null)
                for (i in array.indices)
                    if (array[i]?.giveoutCargo == true)
                        list.add(array[i])
            }
            1->{
                if (array!=null)
                for (i in array.indices)
                    if (array[i]?.receiveCargo == true)
                        list.add(array[i])
            }
        }
        binding.recycler.adapter= RecyclerAdapter(list)
    }
}