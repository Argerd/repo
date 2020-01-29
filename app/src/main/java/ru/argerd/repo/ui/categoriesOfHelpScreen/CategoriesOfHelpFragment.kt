package ru.argerd.repo.ui.categoriesOfHelpScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import ru.argerd.repo.R
import ru.argerd.repo.model.Category
import ru.argerd.repo.presenters.CategoriesPresenter
import ru.argerd.repo.views.CategoriesView

class CategoriesOfHelpFragment : MvpAppCompatFragment(), CategoriesView {
    private lateinit var adapter: AdapterCategories
    private lateinit var recyclerCategories: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var group: Group

    private lateinit var listOfCategory: List<Category?>

    @InjectPresenter
    lateinit var presenter: CategoriesPresenter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_categories_of_help, null)

        recyclerCategories = view.findViewById(R.id.recycler_categories)
        recyclerCategories.layoutManager = GridLayoutManager(context, 2)

        activity?.let {
            val toolbar: Toolbar = activity!!.findViewById(R.id.toolbar)
            val toolbarText = activity!!.findViewById<TextView>(R.id.toolbar_text)

            toolbar.menu.clear()
            toolbarText.setText(R.string.help)
        }

        group = view.findViewById(R.id.categoriesGroup)
        progressBar = view.findViewById(R.id.categoriesProgress)

        adapter = AdapterCategories(context)

        presenter.setListOfCategory()

        val resources = resources
        recyclerCategories.addItemDecoration(Decorator(
                resources.getDimension(R.dimen.margin_item)))

        return view
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun showListOfCategories(list: List<Category?>?) {
        listOfCategory = list ?: listOf()
        adapter.setListOfCategories(listOfCategory)
        adapter.notifyDataSetChanged()
        recyclerCategories.adapter = adapter
        group.visibility = View.VISIBLE
        presenter.hideProgressBar()
    }

    override fun showSnackbarWithError(t: Throwable?) {
        view?.let {
            Snackbar.make(it, t.toString(), Snackbar.LENGTH_LONG).show()
        }
    }
}