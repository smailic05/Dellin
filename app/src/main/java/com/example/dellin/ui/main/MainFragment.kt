package com.example.dellin.ui.main

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.Fragment
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.dellin.*
import com.example.dellin.databinding.MainFragmentBinding

class MainFragment : Fragment(),AppBarInterface {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private val model: MainViewModel by activityViewModels()
    private var appBar:AppBarInterface?=null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        appBar=context as AppBarInterface

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        appBar?.hideAppBar()
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //appBar?.hideAppBar()
        binding.outButton.setOnClickListener {
            binding.outButton.setOnClickListener(null)
            val action=MainFragmentDirections.out()
            action.chooseTab=0
            findNavController().navigate(action)
        }
        binding.inButton.setOnClickListener {
            binding.inButton.setOnClickListener(null)
            val action=MainFragmentDirections.out()
            action.chooseTab=1
            findNavController().navigate(action)
        }
        binding.save.setOnClickListener {
            model.saveOrder()
        }

        if (MainViewModel.firstTerminals!=null)
            {
                binding.textNameOut.text = MainViewModel.firstTerminals?.name
                binding.addressOut.text = MainViewModel.firstTerminals?.address
                binding.latitudeOut.text = MainViewModel.firstTerminals?.latitude
                binding.longitudeOut.text = MainViewModel.firstTerminals?.longitude
                binding.receiveCargoOut.text = MainViewModel.firstTerminals?.receiveCargo.toString()
                binding.giveoutCargoOut.text = MainViewModel.firstTerminals?.giveoutCargo.toString()
                binding.defaultOut.text = MainViewModel.firstTerminals?.defaultTerminal.toString()
                //binding.worktable.text=source.worktable.toString()
                MainViewModel.firstVisibility = VISIBLE
            }
        if (MainViewModel.secondTerminals!=null)
        {
            binding.nameIn.text = MainViewModel.secondTerminals?.name
            binding.addressIn.text = MainViewModel.secondTerminals?.address
            binding.latitudeIn.text = MainViewModel.secondTerminals?.latitude
            binding.longitudeIn.text = MainViewModel.secondTerminals?.longitude
            binding.receiveCargoIn.text = MainViewModel.secondTerminals?.receiveCargo.toString()
            binding.giveoutCargoIn.text = MainViewModel.secondTerminals?.giveoutCargo.toString()
            binding.defaultIn.text = MainViewModel.secondTerminals?.defaultTerminal.toString()
            //binding.worktable.text=source.worktable.toString()
            MainViewModel.secondVisibility = VISIBLE
        }

        binding.outGroup.visibility=MainViewModel.firstVisibility
        binding.inGroup.visibility=MainViewModel.secondVisibility
        if (MainViewModel.firstVisibility== VISIBLE&& MainViewModel.secondVisibility== VISIBLE)
            binding.save.isEnabled=true

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