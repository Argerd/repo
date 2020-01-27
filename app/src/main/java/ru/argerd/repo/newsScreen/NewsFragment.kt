package ru.argerd.repo.newsScreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import kotlinx.android.synthetic.main.activity_main.*
import ru.argerd.repo.App
import ru.argerd.repo.R
import ru.argerd.repo.model.Event
import ru.argerd.repo.parsing.Parser

private const val TAG = "NewsFragment"

class NewsFragment : Fragment() {
    private var newsAdapter: NewsAdapter? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var validEvents: List<Event>? = null
    private lateinit var toolbar: Toolbar

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
            addItemDecoration(Decorator(resources.getDimensionPixelOffset(R.dimen.bottom_margin_news)))
        }

        activity?.findViewById<TextView>(R.id.toolbar_text)?.setText(R.string.news)

        if (App.firstOpenEventsNews) {
            flowableForFile()
                    .map {
                        App.database.eventDao.deleteAllEvents()
                        App.database.eventDao.insertListEvents(it)
                        return@map it
                    }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(dataSubscription())
            App.firstOpenEventsNews = false
        } else {
            Log.d(TAG, "db")
            App.database.eventDao.getAllEvents()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(dataSubscription())
        }

        return view
    }

    private fun flowableForFile(): Flowable<List<Event>> {
        return Flowable.create({ emitter -> emitter.onNext(Parser().getEvents(context!!)) },
                BackpressureStrategy.LATEST)
    }

    private fun dataSubscription(): DisposableSubscriber<List<Event>> {
        return object : DisposableSubscriber<List<Event>>() {
            override fun onComplete() {
            }

            override fun onNext(t: List<Event>?) {
                validEvents = t ?: ArrayList()
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

            override fun onError(t: Throwable?) {
                Log.d(TAG, "error sub ${t?.message}")
            }
        }
    }
}