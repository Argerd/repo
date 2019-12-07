package ru.argerd.repo.newsScreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.argerd.repo.Parser
import ru.argerd.repo.R

private const val TAG = "NewsFragment"

class NewsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.news_list)

        val photos = intArrayOf(R.drawable.news_1, R.drawable.news_2)
        val titles = arrayOf("Спонсоры отремонтируют школу-интернат",
                "Спонсоры отремонтируют школу-интернат")
        val newsContent = arrayOf("Д")

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(Decorator(resources
                    .getDimensionPixelOffset(R.dimen.bottom_margin_news)))
            adapter = NewsAdapter(2, context, photos, titles,
                    newsContent)
        }

        val toolbar: Toolbar = view.findViewById(R.id.news_toolbar)
        toolbar.setOnMenuItemClickListener {
            Navigation.findNavController(container!!).navigate(R.id.filterFragment)
            return@setOnMenuItemClickListener true
        }

        val parser = Parser()
        val list = context?.let { parser.getCategories(it) }

        list?.let {
            Log.d(TAG, "not null")
            Log.d(TAG, "${it.size}")
            for (item in it) {
                Log.d(TAG, "${item.id}")
            }
        }

        return view
    }
}