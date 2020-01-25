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

    private lateinit var listOfCategory: List<Category?>

    companion object {
        private val TAG = CategoriesOfHelpFragment::class.java.name
    }

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
        categoriesBar = view.findViewById(R.id.categoriesProgress)

        adapter = AdapterCategories(context)

        if (App.firstOpenCategory) {
            App.api.getCategories()
                    .map {
                        Log.d(TAG, "запрос к сети")
                        App.database.categoryDao.deleteAllCategories()
                        App.database.categoryDao.insertListCategories(it)
                        return@map it
                    }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(networkSubscription())
        } else {
            App.database.categoryDao.getCategories()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(databaseSubscription())
        }


        val resources = resources
        recyclerCategories.addItemDecoration(Decorator(
                resources.getDimension(R.dimen.margin_item)))

        return view
    }

    private fun networkSubscription(): DisposableSubscriber<List<Category?>> {
        return object : DisposableSubscriber<List<Category?>>() {
            override fun onComplete() {
                recyclerCategories.adapter = adapter
                group.visibility = View.VISIBLE
                categoriesBar.visibility = View.GONE
                App.firstOpenCategory = false
            }

            override fun onNext(t: List<Category?>?) {
                listOfCategory = t ?: ArrayList()
                adapter.setListOfCategories(listOfCategory)
                adapter.notifyDataSetChanged()
            }

            override fun onError(t: Throwable?) {
                view?.let {
                    Snackbar.make(it, R.string.error_network, Snackbar.LENGTH_LONG).show()
                }
                flowableFileParse()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(fileSubscription())
            }
        }
    }

    private fun flowableFileParse(): Flowable<List<Category?>> {
        return Flowable.just(Parser().getCategories(context!!))
    }

    private fun fileSubscription(): DisposableSubscriber<List<Category?>> {
        return object : DisposableSubscriber<List<Category?>>() {
            override fun onComplete() {
                Log.d(TAG, "parsing file complete")
                recyclerCategories.adapter = adapter
                group.visibility = View.VISIBLE
                categoriesBar.visibility = View.GONE
            }

            override fun onNext(t: List<Category?>?) {
                listOfCategory = t ?: ArrayList()
                adapter.setListOfCategories(listOfCategory)
                adapter.notifyDataSetChanged()
            }

            override fun onError(t: Throwable?) {
                Log.d(TAG, "error parsing ${t?.message}")
            }
        }
    }

    private fun databaseSubscription(): DisposableSubscriber<List<Category?>> {
        return object : DisposableSubscriber<List<Category?>>() {
            override fun onComplete() {

            }

            override fun onNext(t: List<Category?>?) {
                Log.d(TAG, "размер листа из бд ${t?.size}")
                listOfCategory = t ?: ArrayList()
                adapter.setListOfCategories(listOfCategory)
                adapter.notifyDataSetChanged()
                Log.d(TAG, "чтение из бд окончено")
                recyclerCategories.adapter = adapter
                group.visibility = View.VISIBLE
                categoriesBar.visibility = View.GONE
            }

            override fun onError(t: Throwable?) {
                Log.d(TAG, "ошибка чтения из бд")
                view?.let {
                    Snackbar.make(it, R.string.database_error, Snackbar.LENGTH_LONG).show()
                }
            }

        }
    }
}