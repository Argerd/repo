package ru.argerd.repo.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import ru.argerd.repo.screens.search.page.PageFragment

class PagerAdapter(fm: FragmentManager, behavior: Int) : FragmentPagerAdapter(fm, behavior) {
    private val tabTitles = arrayOf("По мероприятиям", "По НКО")

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> PageFragment.newInstance(0.toByte())
            1 -> PageFragment.newInstance(1.toByte())
            else -> PageFragment.newInstance(1.toByte())
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }
}