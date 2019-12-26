package ru.argerd.repo.newsScreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Messenger
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.argerd.repo.R
import ru.argerd.repo.model.Event
import ru.argerd.repo.parsing.ExecutorEventsParsing
import ru.argerd.repo.parsing.IntentServiceEventsParsing

private const val SAVE_LIST = "save_list"
private const val TAG = "NewsFragment"

class NewsFragment : Fragment() {
    private var newsAdapter: NewsAdapter? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    //private lateinit var asyncTaskParsing: AsyncTaskEventsParsing
    private lateinit var executorParsing: ExecutorEventsParsing
    private var validEvents: ArrayList<Event>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        progressBar = view.findViewById(R.id.progressBarEvents)

        recyclerView = view.findViewById(R.id.news_list)
        recyclerView.apply {
            visibility = View.GONE
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(Decorator(resources.getDimensionPixelOffset(R.dimen.bottom_margin_news)))
        }

        activity?.findViewById<TextView>(R.id.toolbar_text)?.setText(R.string.news)

        if (savedInstanceState != null) {
            validEvents = savedInstanceState.getParcelableArrayList(SAVE_LIST)
            updateRecycler()
        } else {
            // Вариант с IntentService
            val handler = Handler {
                val replyBundle = it.data
                callbackForUpdate(
                        replyBundle
                                .getParcelableArrayList<Event>(IntentServiceEventsParsing.LIST_EXTRA)
                                ?: ArrayList())
                true
            }
            val intent = Intent(context, IntentServiceEventsParsing::class.java)
            intent.putExtra(IntentServiceEventsParsing.MESSENGER_EXTRA, Messenger(handler))
            activity?.startService(intent)
            // Вариант с Executor
            /* executorParsing = ExecutorEventsParsing(context) { arg -> callbackForUpdate(arg) }
             executorParsing.execute()*/

            // Вариант с AsyncTask
            /*asyncTaskParsing = AsyncTaskEventsParsing(context) { arg -> callbackForUpdate(arg)}
            asyncTaskParsing.execute()*/
        }

        return view
    }

    private fun callbackForUpdate(validEvents: ArrayList<Event>) {
        this.validEvents = validEvents
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        if (newsAdapter == null) {
            newsAdapter = NewsAdapter(validEvents)
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
    }

    private fun updateRecycler() {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        if (newsAdapter == null) {
            newsAdapter = NewsAdapter(validEvents)
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
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(SAVE_LIST, validEvents)
        Log.d(TAG, "valid size before ${validEvents?.size}")
    }
}