package com.example.dellin.ui.main

import android.content.Context
import android.location.Location
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.*
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
import com.google.android.material.snackbar.Snackbar


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
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        if(isConnected)
            model.updateDataInDatabase()
        else
            Snackbar.make(binding.root, "No internet connection", Snackbar.LENGTH_SHORT).show()

        model.snackbar.observe(viewLifecycleOwner,{
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
        })
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
            model.saveOrder(model.firstTerminal.value, model.secondTerminal.value)
        }
//  Проверяем Данные о терминалах и если они есть, то виводим их
        model.firstTerminal.observe(viewLifecycleOwner, {
            binding.textNameOut.text = it?.name
            binding.addressOut.text = it?.address
            binding.locationOut.text = getDistance(DellinApplication.location?.latitude,DellinApplication.location?.
            longitude, it?.latitude?.toDouble(),it?.longitude?.toDouble()).toString()
            val work=undoConvert(it?.worktable!!)
            binding.imageOut.load(it.maps){
                crossfade(true)
                placeholder(R.drawable.no_image)
                transformations(CircleCropTransformation())
                precision(Precision.EXACT)
                scale(Scale.FILL)
                error(R.drawable.no_image)
            }
            binding.viewPagerMain.adapter=MainViewPagerAdapter(work)
            binding.outGroup.visibility = VISIBLE
            if (binding.inGroup.visibility== VISIBLE&& binding.outGroup.visibility== VISIBLE)
                binding.save.isEnabled=true
        })

        model.secondTerminal.observe(viewLifecycleOwner,{
            binding.nameIn.text = it?.name
            binding.addressIn.text = it?.address
            binding.locationIn.text = getDistance(DellinApplication.location?.latitude,DellinApplication.location?.
            longitude, it?.latitude?.toDouble(),it?.longitude?.toDouble()).toString()
            val work=undoConvert(it?.worktable!!)
            binding.imageIn.load(it.maps){
                crossfade(true)
                placeholder(R.drawable.no_image)
                error(R.drawable.no_image)
                transformations(CircleCropTransformation())
            }
            binding.viewPagerMainIn.adapter=MainViewPagerAdapter(work)
            binding.inGroup.visibility = VISIBLE
            if (binding.inGroup.visibility== VISIBLE&& binding.outGroup.visibility== VISIBLE)
                binding.save.isEnabled=true
        })



    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
    private fun undoConvert(worktable: String):Worktables
    {
        val worktableItem= mutableListOf<WorktableItem>()
        val temp=worktable.split("&")
        for (item in temp)
            if(item!="")
                worktableItem.add(WorktableItem.undoConvert(item))
        return Worktables(worktableItem)
    }
}