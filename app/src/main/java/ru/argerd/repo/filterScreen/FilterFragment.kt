package ru.argerd.repo.filterScreen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.argerd.repo.R
import ru.argerd.repo.model.Category

private const val ARG_CATEGORIES = "categories"
private const val FILTER_SETTINGS = "filterSettings"

class FilterFragment : Fragment() {
    private lateinit var listOfFilter: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_filter, container, false)
        val toolbar: Toolbar = view.findViewById(R.id.filter_toolbar)
        listOfFilter = view.findViewById(R.id.list_of_categories)

        val list = arguments?.getParcelableArrayList<Category>(ARG_CATEGORIES)

        val sharedPreferences = activity?.getSharedPreferences(FILTER_SETTINGS, Context.MODE_PRIVATE)
        val settings = sharedPreferences?.all?.keys?.distinct()
        val editor = sharedPreferences?.edit()

        listOfFilter.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = AdapterFilter(list ?: arrayListOf(), editor, settings)
        }

        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            container?.let { Navigation.findNavController(it).popBackStack() }
        }
        toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.filter_ok) {
                editor?.apply()
            }
            return@setOnMenuItemClickListener true
        }
        return view
    }
}