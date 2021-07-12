package com.example.dellin.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dellin.R
import com.example.dellin.Worktables


class MainViewPagerAdapter(private val worktables: Worktables) : RecyclerView.Adapter<MainViewPagerAdapter.ViewHolderPager>() {
    class ViewHolderPager(view: View): RecyclerView.ViewHolder(view) {
        val worktable: TextView = view.findViewById(R.id.worktableIn)
        val monday: TextView = view.findViewById(R.id.mondayIn)
        val tuesday: TextView = view.findViewById(R.id.tuesdayIn)
        val wednesday: TextView = view.findViewById(R.id.wednesdayIn)
        val thursday: TextView = view.findViewById(R.id.thursdayIn)
        val friday: TextView = view.findViewById(R.id.fridayIn)
        val saturday: TextView = view.findViewById(R.id.saturdayIn)
        val sunday: TextView = view.findViewById(R.id.sundayIn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPager {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.worktable_pager, parent, false)

        return ViewHolderPager(view)
    }

    override fun onBindViewHolder(holder: ViewHolderPager, position: Int) {
        holder.worktable.text=worktables.worktable?.get(position)?.department
        holder.monday.text=worktables.worktable?.get(position)?.monday
        holder.tuesday.text=worktables.worktable?.get(position)?.tuesday
        holder.wednesday.text=worktables.worktable?.get(position)?.wednesday
        holder.thursday.text=worktables.worktable?.get(position)?.thursday
        holder.friday.text=worktables.worktable?.get(position)?.friday
        holder.saturday.text=worktables.worktable?.get(position)?.saturday
        holder.sunday.text=worktables.worktable?.get(position)?.sunday
    }

    override fun getItemCount(): Int =worktables.worktable?.size!!
}