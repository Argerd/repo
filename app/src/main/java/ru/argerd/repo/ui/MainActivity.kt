package ru.argerd.repo.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ferfalk.simplesearchview.SimpleSearchView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import moxy.presenter.InjectPresenter
import ru.argerd.repo.R
import ru.argerd.repo.presenters.MainActivityPresenter
import ru.argerd.repo.views.MainActivityView

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), MainActivityView {
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var navController: NavController
    private lateinit var fab: FloatingActionButton
    private lateinit var toolbarText: TextView
    private lateinit var searchView: SimpleSearchView
    private lateinit var editTextSearchView: EditText
    private lateinit var helpText: TextView

    @InjectPresenter
    lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "inOnCreate")
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        bottomNavigation = findViewById(R.id.bottom_navigation_view)
        bottomNavigation.menu.getItem(2).isChecked = true

        toolbarText = findViewById(R.id.toolbar_text)

        helpText = findViewById(R.id.helpText)
        setColorHelpText(true)

        fab = findViewById(R.id.help_button)
        fab.setOnClickListener {
            presenter.fubOnClick()
        }

        searchView = findViewById(R.id.searchView)
        searchView.setBackgroundResource(R.drawable.rectangle_for_search_view)
        editTextSearchView = searchView.findViewById(R.id.searchEditText)
        editTextSearchView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14.toFloat())

        bottomNavigation.setOnNavigationItemSelectedListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.help_item -> presenter.fubOnClick()
                R.id.profile_item -> presenter.navigateToProfile()
                R.id.search_item -> presenter.navigateToSearch()
                R.id.news_item -> presenter.navigateToNews()
            }
            true
        }
    }

    override fun updateUI() {
        navController.currentDestination?.let {
            when (it.id) {
                R.id.categoriesOfHelpFragment -> fab.callOnClick()
                R.id.profileFragment -> {
                    bottomNavigation.menu.getItem(4).isChecked = true
                    setVisibilityToolbarText()
                    setColorHelpText(false)
                }
                R.id.searchFragment -> {
                    bottomNavigation.menu.getItem(1).isChecked = true
                    toolbarText.visibility = View.GONE
                    searchView.visibility = View.VISIBLE
                    setColorHelpText(false)
                }
                R.id.newsFragment -> {
                    bottomNavigation.menu.getItem(0).isChecked = true
                    setVisibilityToolbarText()
                    setColorHelpText(false)
                }
                R.id.filterFragment -> {
                    bottomNavigation.menu.getItem(0).isChecked = true
                    setVisibilityToolbarText()
                    setColorHelpText(false)
                }
                else -> fab.callOnClick()
            }
        }
    }

    private fun setVisibilityToolbarText() {
        toolbarText.apply {
            if (visibility == View.GONE) {
                visibility = View.VISIBLE
                searchView.visibility = View.GONE
            }
        }
    }

    private fun setColorHelpText(isChecked: Boolean) {
        if (isChecked) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                helpText.setTextColor(resources.getColor(R.color.turtle_green, null))
            } else {
                helpText.setTextColor(resources.getColor(R.color.turtle_green))
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                helpText.setTextColor(resources.getColor(R.color.black_40, null))
            } else {
                helpText.setTextColor(resources.getColor(R.color.black_40))
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        presenter.updateUI()
    }

    override fun moveToCategories() {
        if (navController.currentDestination?.id != R.id.categoriesOfHelpFragment) {
            navController.navigate(R.id.categoriesOfHelpFragment)
        }
        setVisibilityToolbarText()
        bottomNavigation.menu.getItem(2).isChecked = true
        setColorHelpText(true)
    }

    override fun navigateToProfile() {
        if (navController.currentDestination?.id != R.id.profileFragment) {
            navController.navigate(R.id.profileFragment)
            setVisibilityToolbarText()
            setColorHelpText(false)
        }
    }

    override fun navigateToSearch() {
        if (navController.currentDestination?.id != R.id.searchFragment) {
            navController.navigate(R.id.searchFragment)
            toolbarText.visibility = View.GONE
            searchView.visibility = View.VISIBLE
            setColorHelpText(false)
        }
    }

    override fun navigateToNews() {
        if (navController.currentDestination?.id != R.id.newsFragment) {
            navController.navigate(R.id.newsScreen)
            setVisibilityToolbarText()
            setColorHelpText(false)
        }
    }
}