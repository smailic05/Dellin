package com.example.dellin.ui.main


import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.dellin.viewModel.MainViewModel
import com.example.dellin.R
import com.example.dellin.TerminalsParsed
import com.example.dellin.databinding.RecyclerLayoutBinding
import com.example.dellin.ui.main.adapters.RecyclerAdapter

class PagerFragment(private val position: Int) : Fragment() {
    private var _binding: RecyclerLayoutBinding? = null
    private val binding get() = _binding!!

    private val model: MainViewModel by activityViewModels()
    private val list= mutableListOf<TerminalsParsed?>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RecyclerLayoutBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        model.arrayOfTerminalsParsed.observe(viewLifecycleOwner,{
            val array = it
            // Заполняем контейнер нужными данными и отправляем их в адаптер
            when (position) {
                0 -> {
                    if (array != null)
                        for (i in array.indices)
                            if (array[i]?.giveoutCargo == true && array[i]?.giveoutCargo ==true)
                                list.add(array[i])
                }
                1 -> {
                    if (array != null)
                        for (i in array.indices)
                            if (array[i]?.receiveCargo == true)
                                list.add(array[i])
                }
            }
            binding.recycler.adapter = RecyclerAdapter(list, position)
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        val myActionMenuItem = menu.findItem(R.id.search)
        val searchView=myActionMenuItem.actionView as SearchView
        //Реализация поиска
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query!=null)
                (binding.recycler.adapter as RecyclerAdapter).filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!=null)
                    (binding.recycler.adapter as RecyclerAdapter).filter(newText)
                return true
            }
        })
        //Сортировка по алфавиту
        menu.findItem(R.id.sort).setOnMenuItemClickListener {
            (binding.recycler.adapter as RecyclerAdapter).sort()
            true
        }
        //Сортировка по удаленности
        menu.findItem(R.id.sortLocation).setOnMenuItemClickListener {
            (binding.recycler.adapter as RecyclerAdapter).sortByLocation()
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}