package com.example.dellin.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.dellin.Dellin
import com.example.dellin.R
import com.example.dellin.TerminalsParsed
import com.example.dellin.ui.main.MainFragment
import com.example.dellin.ui.main.SecondFragmentDirections
import java.util.*
import kotlin.math.pow

class RecyclerAdapter(private val dataSet: MutableList<TerminalsParsed?>, private val page:Int):
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private val dataSetCopy= mutableListOf<TerminalsParsed?>()
    private var isSortedByAbc=true
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
                0->MainFragment.firstTerminals=amount
                1->MainFragment.secondTerminals=amount
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
        if (isSortedByAbc)
        {
            dataSet.reverse()
            isSortedByAbc=false
            notifyDataSetChanged()
        }
        else{
            isSortedByAbc=true
            notifyDataSetChanged()
        }
    }
    fun sortByLocation(){
        if (Dellin.location!=null)
        dataSet.sortBy { ((it?.longitude?.toDouble()?.minus(Dellin.location!!.longitude))?.pow(2)
            ?.plus((it.latitude?.toDouble()?.minus(Dellin.location!!.latitude))?.pow(2)!!))?.pow(0.5) }
        notifyDataSetChanged()
        isSortedByAbc=false
    }
}