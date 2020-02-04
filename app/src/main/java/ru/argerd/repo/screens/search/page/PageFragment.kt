package ru.argerd.repo.screens.search.page

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ScrollView
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.widget.textChanges
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import ru.argerd.repo.R
import ru.argerd.repo.adapters.ListAdapter
import java.util.*

class PageFragment : MvpAppCompatFragment(), PageView {
    private lateinit var listOfItems: RecyclerView
    private lateinit var adapter: ListAdapter
    private lateinit var emptyContent: ScrollView
    private lateinit var groupResults: Group
    private lateinit var searchText: EditText

    @InjectPresenter
    lateinit var presenter: PagePresenter

    private var type: Byte = 0

    companion object {
        private const val ARG_PAGE = "ARG_PAGE"
        private const val TAG = "PageFragment"

        fun newInstance(type: Byte): PageFragment {
            val bundle = Bundle()
            bundle.putByte(ARG_PAGE, type)
            val pageFragment = PageFragment()
            pageFragment.arguments = bundle
            return pageFragment
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            type = arguments!!.getByte(ARG_PAGE)
        }
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_page, null)

        listOfItems = view.findViewById(R.id.list_with_results)
        adapter = ListAdapter(ArrayList(), context!!)
        listOfItems.adapter = adapter
        listOfItems.layoutManager = LinearLayoutManager(context)

        emptyContent = view.findViewById(R.id.emptyContent)
        groupResults = activity!!.findViewById(R.id.results)

        if (type.toInt() == 0) {
            searchText = activity!!.findViewById<View>(R.id.searchView)
                    .findViewById(R.id.searchEditText)
            presenter.onSearch(searchText.textChanges().skipInitialValue())
        } else {
            val example = ArrayList<String>()
            example.add("3")
            example.add("4")
            adapter = ListAdapter(example, context!!)
            listOfItems.adapter = adapter
            emptyContent.visibility = View.GONE
        }
        return view
    }

    override fun skipListOfResults() {
        emptyContent.visibility = View.VISIBLE
        listOfItems.visibility = View.GONE
        groupResults.visibility = View.GONE
    }

    override fun showListOfResults(list: List<String?>) {
        groupResults.visibility = View.VISIBLE
        Log.d(TAG, "update list")
        adapter.items = list
        adapter.notifyDataSetChanged()
        listOfItems.visibility = View.VISIBLE
        groupResults.visibility = View.VISIBLE
        emptyContent.visibility = View.GONE
    }
}