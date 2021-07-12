package com.example.dellin.ui.main.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dellin.ui.main.PagerFragment


class ViewPagerFragmentAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2
    //Создаем новый фрагмент и передаем ему его позицию
    override fun createFragment(position: Int): Fragment= PagerFragment(position)

}