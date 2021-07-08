package com.example.dellin.ui.main

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.Fragment
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.navigation.fragment.findNavController
import coil.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.dellin.*
import com.example.dellin.databinding.MainFragmentBinding
import com.example.dellin.ui.main.adapters.MainViewPagerAdapter

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
            model.saveOrder(firstTerminals, secondTerminals)
        }

        if (firstTerminals!=null)
            {
                binding.textNameOut.text = firstTerminals?.name
                binding.addressOut.text = firstTerminals?.address
                binding.latitudeOut.text = firstTerminals?.latitude
                binding.longitudeOut.text = firstTerminals?.longitude
                binding.receiveCargoOut.text = firstTerminals?.receiveCargo.toString()
                binding.giveoutCargoOut.text = firstTerminals?.giveoutCargo.toString()
                binding.defaultOut.text = firstTerminals?.defaultTerminal.toString()
                val work=Worktables.undoConvert(firstTerminals?.worktable!!)
                binding.imageOut.load(firstTerminals?.maps){
                    crossfade(true)
                    placeholder(R.drawable.no_image)
                    transformations(CircleCropTransformation())
                    scale(Scale.FILL)
                }
                binding.viewPagerMain.adapter=MainViewPagerAdapter(work)
                firstVisibility = VISIBLE
            }
        if (secondTerminals!=null)
        {
            binding.nameIn.text = secondTerminals?.name
            binding.addressIn.text = secondTerminals?.address
            binding.latitudeIn.text = secondTerminals?.latitude
            binding.longitudeIn.text = secondTerminals?.longitude
            binding.receiveCargoIn.text = secondTerminals?.receiveCargo.toString()
            binding.giveoutCargoIn.text = secondTerminals?.giveoutCargo.toString()
            binding.defaultIn.text = secondTerminals?.defaultTerminal.toString()
            val work=Worktables.undoConvert(secondTerminals?.worktable!!)
            binding.imageIn.load(secondTerminals?.maps){
                crossfade(true)
                placeholder(R.drawable.no_image)
                transformations(CircleCropTransformation())
            }
            binding.viewPagerMainIn.adapter=MainViewPagerAdapter(work)
            secondVisibility = VISIBLE
        }

        binding.outGroup.visibility=firstVisibility
        binding.inGroup.visibility=secondVisibility
        if (firstVisibility== VISIBLE&& secondVisibility== VISIBLE)
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
    companion object{
        var firstVisibility:Int= INVISIBLE
        var secondVisibility:Int= INVISIBLE
        var firstTerminals:TerminalsParsed?=null
        var secondTerminals:TerminalsParsed?=null
    }


}