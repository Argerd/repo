package ru.argerd.repo.screens.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import ru.argerd.repo.R
import ru.argerd.repo.adapters.PagerAdapter

class SearchFragment : MvpAppCompatFragment(), SearchView {

    @InjectPresenter
    lateinit var presenter: SearchPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragmen_search, container, false)
        val toolbar: Toolbar = activity!!.findViewById(R.id.toolbar)
        toolbar.menu.clear()

        val viewPager: ViewPager = view.findViewById(R.id.fragment_pager)
        val pagerAdapter = PagerAdapter(childFragmentManager,
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        viewPager.adapter = pagerAdapter

        val tabLayout: TabLayout = view.findViewById(R.id.fragment_tab)
        tabLayout.setupWithViewPager(viewPager)

        val searchResults = view.findViewById<TextView>(R.id.search_results)
        val keywords = view.findViewById<TextView>(R.id.keywords)
        keywords.append(" мастер-класс, помощь")
        searchResults.append(" 109 мероприятий")

        return view
    }
}