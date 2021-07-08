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
                parseWorktableOut(work.worktable?.get(0))
                val disposable=binding.imageOut.load(firstTerminals?.maps){
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
            parseWorktableIn(Worktables.undoConvert(secondTerminals?.worktable!!).worktable?.get(0))
            binding.imageIn.load(secondTerminals?.maps){
                crossfade(true)
                placeholder(R.drawable.no_image)
                transformations(CircleCropTransformation())
            }
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

    private fun parseWorktableOut(worktables: WorktableItem?)
    {
        binding.worktable.text=worktables?.department
        binding.monday.text=worktables?.monday
        binding.tuesday.text=worktables?.tuesday
        binding.wednesday.text=worktables?.wednesday
        binding.thursday.text=worktables?.thursday
        binding.friday.text=worktables?.friday
        binding.saturday.text=worktables?.saturday
        binding.sunday.text=worktables?.sunday
    }
    private fun parseWorktableIn(worktables: WorktableItem?)
    {
        binding.worktableIn.text=worktables?.department
        binding.mondayIn.text=worktables?.monday
        binding.tuesdayIn.text=worktables?.tuesday
        binding.wednesdayIn.text=worktables?.wednesday
        binding.thursdayIn.text=worktables?.thursday
        binding.fridayIn.text=worktables?.friday
        binding.saturdayIn.text=worktables?.saturday
        binding.sundayIn.text=worktables?.sunday
    }
}