package com.example.dellin.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.dellin.DellinApplication
import com.example.dellin.R
import com.example.dellin.TerminalsParsed
import com.example.dellin.ui.main.MainFragment
import com.example.dellin.ui.main.SecondFragmentDirections
import java.util.*
import kotlin.math.pow

class RecyclerAdapter(private val dataSet: MutableList<TerminalsParsed?>,
                      private val page:Int, private val firstTerminal: MutableLiveData<TerminalsParsed?>,
                      private val secondTerminal: MutableLiveData<TerminalsParsed?>
):
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
                0->firstTerminal.value=amount
                1->secondTerminal.value=amount
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
    //?????????????? ???????????????????? ???????????????????? ???? ????????????????
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
    //?????????????? ???????????????????? ???????????????????? ???? ??????????????????????
    fun sortByLocation(){
        if (DellinApplication.location!=null)
        dataSet.sortBy { ((it?.longitude?.toDouble()?.minus(DellinApplication.location!!.longitude))?.pow(2)
            ?.plus((it.latitude?.toDouble()?.minus(DellinApplication.location!!.latitude))?.pow(2)!!))?.pow(0.5) }
        notifyDataSetChanged()
        isSortedByAbc=false
    }
}