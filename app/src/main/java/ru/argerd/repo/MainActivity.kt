package ru.argerd.repo

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ferfalk.simplesearchview.SimpleSearchView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.argerd.repo.filterScreen.ARG_CATEGORIES
import ru.argerd.repo.model.Category
import ru.argerd.repo.parsing.AsyncTaskCategoriesParsing
import ru.argerd.repo.parsing.ExecutorCategoriesParsing

private const val TAG = "MainActivity"
private const val SAVED_CATEGORIES = "save"

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var navController: NavController
    private lateinit var fab: FloatingActionButton
    private lateinit var toolbar: Toolbar
    private lateinit var toolbarText: TextView
    private lateinit var searchView: SimpleSearchView
    private lateinit var helpText: TextView
    private var listCategories: ArrayList<Category>? = null
    private lateinit var progressBar: ProgressBar
    private lateinit var fragmentContainer: FrameLayout
    private lateinit var taskCategories: AsyncTaskCategoriesParsing

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "inOnCreate")
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBarCategories)
        fragmentContainer = findViewById(R.id.fragment_container)
        fragmentContainer.visibility = View.GONE

        if (savedInstanceState != null) {
            listCategories = savedInstanceState.getParcelableArrayList(SAVED_CATEGORIES)
            progressBar.visibility = View.GONE
            fragmentContainer.visibility = View.VISIBLE
            Log.d(TAG, "after restore ${listCategories?.size ?: -1}")
        } else {
            // Вариант с IntentService
            /*val handler = Handler {
                val replyBundle = it.data
                listCategories = replyBundle
                        .getParcelableArrayList(IntentServiceCategoriesParsing.LIST_EXTRA)
                progressBar.visibility = View.GONE
                fragmentContainer.visibility = View.VISIBLE
                true
            }
            val intent = Intent(this, IntentServiceCategoriesParsing::class.java)
            intent.putExtra(IntentServiceCategoriesParsing.MESSENGER_EXTRA, Messenger(handler))
            startService(intent)*/
            // Вариант с Executor
            ExecutorCategoriesParsing(this) { arg -> callbackForExecutor(arg) }.execute()
            // Вариант с AsyncTask
            /*taskCategories = AsyncTaskCategoriesParsing(this) { callbackForAsyncTask() }
            taskCategories.execute()*/
        }

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
        hint.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14.toFloat())

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
                        setToolbarForNews()
                    }
                }
            }
            true
        }
    }

    private fun callbackForExecutor(list: ArrayList<Category>?) {
        this.listCategories = list
        progressBar.visibility = View.GONE
        fragmentContainer.visibility = View.VISIBLE
        Log.d(TAG, "after update ${listCategories?.size ?: -1}")
    }

    private fun callbackForAsyncTask() {
        progressBar.visibility = View.GONE
        fragmentContainer.visibility = View.VISIBLE
        listCategories = taskCategories.get()
        Log.d(TAG, "after update ${listCategories?.size ?: -1}")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        navController.currentDestination?.let {
            when (it.id) {
                R.id.newsFragment -> {
                    bottomNavigation.menu.getItem(0).isChecked = true
                    visibilityToolbarText()
                    setColorHelpText(false)
                    setToolbarForNews()
                }
                R.id.filterFragment -> {
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

    private fun setToolbarForNews() {
        toolbar.apply {
            menu.clear()
            inflateMenu(R.menu.filter_of_news_menu)
            setOnMenuItemClickListener {
                val bundle = Bundle()
                menu.clear()
                bundle.putParcelableArrayList(ARG_CATEGORIES,
                        listCategories)
                navController.navigate(R.id.filterFragment, bundle)
                return@setOnMenuItemClickListener true
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "before saving ${listCategories?.size ?: -1}")
        outState.putParcelableArrayList(SAVED_CATEGORIES, listCategories)
    }
}