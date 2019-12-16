package ru.argerd.repo

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ferfalk.simplesearchview.SimpleSearchView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.argerd.repo.model.Category

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var navController: NavController
    private lateinit var fab: FloatingActionButton
    private lateinit var toolbar: Toolbar
    private lateinit var toolbarText: TextView
    private lateinit var searchView: SimpleSearchView
    private lateinit var helpText: TextView

    private lateinit var listOfCategories: List<Category>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "inOnCreate")
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        bottomNavigation = findViewById(R.id.bottom_navigation_view)
        bottomNavigation.menu.getItem(2).isChecked = true

        toolbarText = findViewById(R.id.toolbar_text)
        toolbarText.text = resources.getString(R.string.help)
        toolbar = findViewById(R.id.toolbar)

        helpText = findViewById(R.id.helpText)
        setColorHelpText(true)

        fab = findViewById(R.id.help_button)
        fab.setOnClickListener {
            if (navController.currentDestination?.id != R.id.categoriesOfHelpFragment) {
                navController.navigate(R.id.categoriesOfHelpFragment)
            }
            visibilityToolbarText()
            toolbarText.text = resources.getString(R.string.help)
            toolbar.menu.clear()
            bottomNavigation.menu.getItem(2).isChecked = true
            setColorHelpText(true)
        }

        searchView = findViewById(R.id.searchView)
        searchView.setBackgroundResource(R.drawable.rectangle_for_search_view)

        val hint: EditText = searchView.findViewById(R.id.searchEditText)
        hint.textSize = resources.getDimension(R.dimen.searchViewTextSize)

        bottomNavigation.setOnNavigationItemSelectedListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.help_item -> fab.callOnClick()
                R.id.profile_item -> {
                    if (navController.currentDestination?.id != R.id.profileFragment) {
                        navController.navigate(R.id.profileFragment)
                        visibilityToolbarText()
                        setColorHelpText(false)
                    }
                }
                R.id.search_item -> {
                    if (navController.currentDestination?.id != R.id.searchFragment) {
                        navController.navigate(R.id.searchFragment)
                        toolbarText.visibility = View.GONE
                        searchView.visibility = View.VISIBLE
                        setColorHelpText(false)
                    }
                }
                R.id.news_item -> {
                    if (navController.currentDestination?.id != R.id.newsFragment) {
                        navController.navigate(R.id.newsScreen)
                        visibilityToolbarText()
                        setColorHelpText(false)
                    }
                }
            }
            true
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        navController.currentDestination?.let {
            when (it.id) {
                R.id.newsFragment -> {
                    bottomNavigation.menu.getItem(0).isChecked = true
                    visibilityToolbarText()
                    setColorHelpText(false)
                }
                R.id.searchFragment -> {
                    bottomNavigation.menu.getItem(1).isChecked = true
                    toolbarText.visibility = View.GONE
                    searchView.visibility = View.VISIBLE
                    setColorHelpText(false)
                }
                R.id.categoriesOfHelpFragment -> fab.callOnClick()
                R.id.profileFragment -> {
                    bottomNavigation.menu.getItem(4).isChecked = true
                    visibilityToolbarText()
                    setColorHelpText(false)
                }
                R.id.filterFragment -> {
                    bottomNavigation.menu.getItem(0).isChecked = true
                    visibilityToolbarText()
                    setColorHelpText(false)
                }
                else -> fab.callOnClick()
            }
        }
    }

    private fun visibilityToolbarText() {
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
}