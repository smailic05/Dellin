package com.example.dellin.ui.main.adapters

import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.dellin.R
import com.example.dellin.TerminalsParsed
import com.example.dellin.ui.main.MainFragmentDirections
import com.example.dellin.ui.main.SecondFragmentDirections

class RecyclerAdapter(private val dataSet: List<TerminalsParsed?>):
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
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
            action.clickArgs=position
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = dataSet.size
}