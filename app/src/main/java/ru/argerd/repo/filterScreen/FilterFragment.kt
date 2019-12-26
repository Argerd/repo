package ru.argerd.repo.filterScreen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.argerd.repo.R
import ru.argerd.repo.model.Category

private const val FILTER_SETTINGS = "filterSettings"
const val ARG_CATEGORIES = "categories"

class FilterFragment : Fragment() {
    private lateinit var listOfFilter: RecyclerView
    private var toolbar: Toolbar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_filter, container, false)

        listOfFilter = view.findViewById(R.id.list_of_categories)

        val list = arguments?.getParcelableArrayList<Category>(ARG_CATEGORIES)

        val sharedPreferences = activity?.getSharedPreferences(FILTER_SETTINGS, Context.MODE_PRIVATE)
        val settings = sharedPreferences?.all?.keys?.distinct()
        val editor = sharedPreferences?.edit()

        listOfFilter.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = AdapterFilter(list ?: arrayListOf(), editor, settings)
        }

        activity?.findViewById<TextView>(R.id.toolbar_text)?.setText(R.string.filter)
        toolbar = activity?.findViewById(R.id.toolbar)
        toolbar?.apply {
            menu.clear()
            navigationIcon = resources.getDrawable(R.drawable.ic_back, null)
            setNavigationOnClickListener {
                container?.let {
                    activity?.onBackPressed()
                }
            }
            inflateMenu(R.menu.filter_ok_menu)
            setNavigationIcon(R.drawable.ic_back)
            setOnMenuItemClickListener {
                if (it.itemId == R.id.filter_ok) {
                    editor?.apply()
                }
                return@setOnMenuItemClickListener true
            }
        }

        return view
    }

    override fun onPause() {
        toolbar?.navigationIcon = null
        super.onPause()
    }
}