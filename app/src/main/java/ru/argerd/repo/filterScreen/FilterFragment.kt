package ru.argerd.repo.filterScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import ru.argerd.repo.R

class FilterFragment : Fragment() {
    private lateinit var listOfFilter: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_filter, container, false)
        val toolbar: Toolbar = view.findViewById(R.id.filter_toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            container?.let { Navigation.findNavController(it).popBackStack() }
        }

        listOfFilter = view.findViewById(R.id.list_of_categories)

        return view
    }
}