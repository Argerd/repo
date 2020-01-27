package ru.argerd.repo.filterScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import ru.argerd.repo.R
import ru.argerd.repo.model.Category

class FilterFragment : MvpAppCompatFragment(), FilterView {
    private lateinit var listOfFilter: RecyclerView
    private var toolbar: Toolbar? = null
    private lateinit var adapterFilter: AdapterFilter

    @InjectPresenter
    lateinit var presenter: FilterPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_filter, container, false)

        listOfFilter = view.findViewById(R.id.list_of_categories)

        adapterFilter = AdapterFilter { arg, arg1 -> presenter.onSwitchCategory(arg, arg1) }

        listOfFilter.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterFilter
        }

        presenter.showList()

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
                    presenter.applyChanges()
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

    override fun showList(t: List<Category>?, settings: List<String>) {
        adapterFilter.categories = t ?: listOf()
        adapterFilter.settings = settings
        adapterFilter.notifyDataSetChanged()
    }
}