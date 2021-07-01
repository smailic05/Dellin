package com.example.dellin.ui.main

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.dellin.MainViewModel
import com.example.dellin.R
import com.example.dellin.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private val args: MainFragmentArgs by navArgs()
    companion object {
        fun newInstance() = MainFragment()
    }
    private val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.outButton.setOnClickListener {
            val action=MainFragmentDirections.out()
            action.chooseTab=0
            findNavController().navigate(action)
        }
        binding.inButton.setOnClickListener {
            val action=MainFragmentDirections.out()
            action.chooseTab=1
            findNavController().navigate(action)
        }
        if(args.clickArgs!=-1)
        {
            val source= model.array?.get(args.clickArgs)
            if (source!=null)
            {
                binding.textNameOut.text=source.name
                binding.addressOut.text=source.address
                binding.latitudeOut.text=source.latitude
                binding.longitudeOut.text=source.longitude
                binding.receiveCargoOut.text=source.receiveCargo.toString()
                binding.giveoutCargoOut.text=source.giveoutCargo.toString()
                binding.defaultOut.text=source.defaultTerminal.toString()
                //binding.worktable.text=source.worktable.toString()
                binding.outGroup.visibility= VISIBLE
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}