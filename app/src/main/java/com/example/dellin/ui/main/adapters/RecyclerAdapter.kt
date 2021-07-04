package com.example.dellin.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.dellin.Dellin
import com.example.dellin.MainViewModel
import com.example.dellin.R
import com.example.dellin.TerminalsParsed
import com.example.dellin.ui.main.SecondFragmentDirections
import java.util.*
import java.util.Collections.addAll
import kotlin.math.pow

class RecyclerAdapter(private val dataSet: MutableList<TerminalsParsed?>, private val page:Int):
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private val dataSetCopy= mutableListOf<TerminalsParsed?>()
    init {
        dataSetCopy.addAll(dataSet)
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.terminals)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_terminals, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val amount=dataSet[position]
        holder.textView.text=amount?.name
        holder.textView.setOnClickListener {
            val action = SecondFragmentDirections.clicked()
            when(page)
            {
                0->MainViewModel.firstTerminals=amount
                1->MainViewModel.secondTerminals=amount
            }
            it.findNavController().navigate(action)

        }
    }

    override fun getItemCount(): Int = dataSet.size

    fun filter(text: String) {
        val findText: String
        dataSet.clear()
        if (text.isEmpty()) {
            dataSet.addAll(dataSetCopy)
        } else {
            findText = text.lowercase(Locale.getDefault())
            for (item in dataSetCopy) {
                if (item?.name?.lowercase(Locale.ROOT)!!.contains(findText))
                {
                    dataSet.add(item)
                }
            }
        }
        notifyDataSetChanged()
    }
    fun sort() {
        dataSet.sortBy{it?.name}
        notifyDataSetChanged()
    }
    fun sortByLocation(){
        dataSet.sortBy { ((it?.longitude?.toDouble()?.minus(Dellin.location.longitude))?.pow(2)
            ?.plus((it.latitude?.toDouble()?.minus(Dellin.location.latitude))?.pow(2)!!))?.pow(0.5) }// TODO сделать сортировку в репозитории
        notifyDataSetChanged()
    }
}