package ru.argerd.repo.newsScreen

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.argerd.repo.Parser
import ru.argerd.repo.R
import ru.argerd.repo.model.Event

private const val TAG = "NewsFragment"
private const val ARG_CATEGORIES = "categories"
private const val FILTER_SETTINGS = "filterSettings"

class NewsFragment : Fragment() {
    private val photo = R.drawable.news_1
    private val parser = Parser()

    private var sharedPreferences: SharedPreferences? = null
    private var settings: List<String>? = null
    private var validEvents: MutableList<Event> = mutableListOf()
    private var newsAdapter: NewsAdapter? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var listEvents: List<Event>
    private var settingsSize = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        recyclerView = view.findViewById(R.id.news_list)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(Decorator(resources.getDimensionPixelOffset(R.dimen.bottom_margin_news)))
        }

        val listCategories = context?.let { parser.getCategories(it) }
        context?.let {
            listEvents = parser.getEvents(it)
        }

        sharedPreferences = activity?.getSharedPreferences(FILTER_SETTINGS, Context.MODE_PRIVATE)

        settings = sharedPreferences?.all?.keys?.distinct()

        var counter = 0
        settings?.let { settings ->
            if (settingsSize != settings.size || validEvents.isEmpty()) {
                settingsSize = settings.size
                if (settings.isEmpty()) {
                    validEvents = listEvents.toMutableList()
                } else {
                    validEvents.clear()
                    for (i in listEvents.indices) {
                        listEvents[i].categories?.let { category ->
                            for (j in category.indices) {
                                if (settings.contains(category[j]?.name)) {
                                    counter++
                                }
                                if (counter == settings.size) {
                                    validEvents.add(listEvents[i])
                                    counter = 0
                                }
                            }
                        }
                    }
                }
            }
        }
        Log.d(TAG, "valid events size ${validEvents.size}")
        if (newsAdapter == null) {
            newsAdapter = NewsAdapter(photo, validEvents)
            recyclerView.adapter = newsAdapter
        } else {
            newsAdapter?.let {
                val utils = NewsDiffUtils(it.events, validEvents)
                val utilsResult = DiffUtil.calculateDiff(utils)
                it.events = validEvents
                utilsResult.dispatchUpdatesTo(it)
                recyclerView.adapter = it
            }
        }

        Log.d(TAG, "setting size ${settings!!.size}")

        val toolbar: Toolbar = view.findViewById(R.id.news_toolbar)
        toolbar.setOnMenuItemClickListener {
            val bundle = Bundle()
            bundle.putParcelableArrayList(ARG_CATEGORIES, listCategories)
            container?.let {
                Navigation.findNavController(it).navigate(R.id.filterFragment, bundle)
            }
            return@setOnMenuItemClickListener true
        }

        return view
    }
}