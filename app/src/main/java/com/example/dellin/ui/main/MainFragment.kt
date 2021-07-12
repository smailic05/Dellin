package com.example.dellin.ui.main

import android.content.Context
import android.location.Location
import android.os.Bundle
import android.view.*
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import coil.size.Precision
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.dellin.*
import com.example.dellin.databinding.MainFragmentBinding
import com.example.dellin.ui.main.adapters.MainViewPagerAdapter
import com.example.dellin.viewModel.MainViewModel


class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private val model: MainViewModel by activityViewModels()
    private var appBar: AppBarInterface?=null
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
        (activity as AppCompatActivity).supportActionBar?.title=null
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Запрашиваем данные с сервера
        model.createRequest()
        // Для кнопок создаем лисенеры
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
//  Проверяем Данные о терминалах и если они есть, то виводим их
        if (firstTerminals!=null)
            {
                binding.textNameOut.text = firstTerminals?.name
                binding.addressOut.text = firstTerminals?.address
                binding.locationOut.text = getDistance(Dellin.location?.latitude,Dellin.location?.longitude, firstTerminals?.latitude?.toDouble(),firstTerminals?.longitude?.toDouble()).toString()
                val work=Worktables.undoConvert(firstTerminals?.worktable!!)
                binding.imageOut.load(firstTerminals?.maps){
                    crossfade(true)
                    placeholder(R.drawable.no_image)
                    transformations(CircleCropTransformation())
                    precision(Precision.EXACT)
                    scale(Scale.FILL)
                    error(R.drawable.no_image)
                }
                binding.viewPagerMain.adapter=MainViewPagerAdapter(work)
                firstVisibility = VISIBLE
            }
        if (secondTerminals!=null)
        {
            binding.nameIn.text = secondTerminals?.name
            binding.addressIn.text = secondTerminals?.address
            binding.locationIn.text = getDistance(Dellin.location?.latitude,Dellin.location?.longitude, secondTerminals?.latitude?.toDouble(),secondTerminals?.longitude?.toDouble()).toString()
            val work=Worktables.undoConvert(secondTerminals?.worktable!!)
            binding.imageIn.load(secondTerminals?.maps){
                crossfade(true)
                placeholder(R.drawable.no_image)
                error(R.drawable.no_image)
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

    companion object{
        var firstVisibility:Int= INVISIBLE
        var secondVisibility:Int= INVISIBLE
        var firstTerminals:TerminalsParsed?=null
        var secondTerminals:TerminalsParsed?=null
    }

    //Функция определения удаленности от пользователя терминала
    private fun getDistance(main_Latitude:Double?, main_Longitude:Double?, sub_Latitude:Double?, sub_Longitude:Double?):Double
    {
        val oldlocation = Location("")
        val newlocation = Location("")
        try {
            oldlocation.latitude = main_Latitude!!
            oldlocation.longitude = main_Longitude!!
            newlocation.latitude = sub_Latitude!!
            newlocation.longitude = sub_Longitude!!
        }
        catch (e: Exception)
        {
            return 0.0
        }
        return oldlocation.distanceTo(newlocation).toDouble()
    }

}