package ru.argerd.repo.screens.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import ru.argerd.repo.R
import ru.argerd.repo.adapters.NewsAdapter
import ru.argerd.repo.data.model.Event
import ru.argerd.repo.utils.NewsDecorator
import ru.argerd.repo.utils.NewsDiffUtils

class NewsFragment : MvpAppCompatFragment(), NewsView {
    private var newsAdapter: NewsAdapter? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var validEvents: List<Event>? = null
    private lateinit var toolbar: Toolbar

    @InjectPresenter
    lateinit var presenter: NewsPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        progressBar = view.findViewById(R.id.progressBarEvents)

        toolbar = activity!!.findViewById(R.id.toolbar)
        toolbar.apply {
            menu.clear()
            inflateMenu(R.menu.filter_of_news_menu)
            setOnMenuItemClickListener {
                menu.clear()
                NavHostFragment.findNavController(nav_host_fragment).navigate(R.id.filterFragment)
                return@setOnMenuItemClickListener true
            }
        }

        recyclerView = view.findViewById(R.id.news_list)
        recyclerView.apply {
            visibility = View.GONE
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(NewsDecorator(resources.getDimensionPixelOffset(R.dimen.bottom_margin_news)))
        }

        activity?.findViewById<TextView>(R.id.toolbar_text)?.setText(R.string.news)

        presenter.showList()

        return view
    }

    override fun showList(list: List<Event>?) {
        validEvents = list ?: ArrayList()
        newsAdapter = NewsAdapter()
        val utils = NewsDiffUtils(newsAdapter?.events, validEvents)
        val utilsResult = DiffUtil.calculateDiff(utils)
        newsAdapter?.events = validEvents ?: ArrayList()
        utilsResult.dispatchUpdatesTo(newsAdapter!!)
        recyclerView.adapter = newsAdapter
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        recyclerView.adapter = newsAdapter
    }
}