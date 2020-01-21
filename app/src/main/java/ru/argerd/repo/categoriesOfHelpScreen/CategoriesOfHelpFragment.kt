package ru.argerd.repo.categoriesOfHelpScreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import ru.argerd.repo.App
import ru.argerd.repo.R
import ru.argerd.repo.model.Category
import ru.argerd.repo.parsing.Parser

class CategoriesOfHelpFragment : Fragment() {
    private lateinit var adapter: AdapterCategories
    private lateinit var recyclerCategories: RecyclerView
    private lateinit var categoriesBar: ProgressBar
    private lateinit var group: Group

    companion object {
        private val TAG = CategoriesOfHelpFragment::class.java.name
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_categories_of_help, null)

        recyclerCategories = view.findViewById(R.id.recycler_categories)
        recyclerCategories.layoutManager = GridLayoutManager(context, 2)

        val toolbar: Toolbar = activity!!.findViewById(R.id.toolbar)
        toolbar.menu.clear()

        val toolbarText = activity!!.findViewById<TextView>(R.id.toolbar_text)
        toolbarText.setText(R.string.help)

        group = view.findViewById(R.id.categoriesGroup)
        categoriesBar = view.findViewById(R.id.categoriesProgress)

        adapter = AdapterCategories(context)

        App.api?.getCategories()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(networkSubscriber())

        val resources = resources
        recyclerCategories.addItemDecoration(Decorator(
                resources.getDimension(R.dimen.margin_item)))
        return view
    }

    private fun networkSubscriber(): DisposableSubscriber<ArrayList<Category?>> {
        return object : DisposableSubscriber<ArrayList<Category?>>() {
            override fun onComplete() {
                recyclerCategories.adapter = adapter
                group.visibility = View.VISIBLE
                categoriesBar.visibility = View.GONE
            }

            override fun onNext(t: ArrayList<Category?>?) {
                Log.d(TAG, "onNext")
                adapter.setListOfCategories(t)
                adapter.notifyDataSetChanged()
            }

            override fun onError(t: Throwable?) {
                view?.let {
                    Snackbar.make(it, R.string.error_network, Snackbar.LENGTH_LONG).show()
                }
                flowableFileParse()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(fileSubscriber())
            }
        }
    }

    private fun flowableFileParse(): Flowable<ArrayList<Category?>> {
        return Flowable.just(Parser().getCategories(context!!))
    }


    private fun fileSubscriber(): DisposableSubscriber<ArrayList<Category?>> {
        return object : DisposableSubscriber<ArrayList<Category?>>() {
            override fun onComplete() {
                recyclerCategories.adapter = adapter
                group.visibility = View.VISIBLE
                categoriesBar.visibility = View.GONE
            }

            override fun onNext(t: ArrayList<Category?>?) {
                Log.d(TAG, "onNext")
                adapter.setListOfCategories(t)
                adapter.notifyDataSetChanged()
            }

            override fun onError(t: Throwable?) {
            }

        }
    }
}