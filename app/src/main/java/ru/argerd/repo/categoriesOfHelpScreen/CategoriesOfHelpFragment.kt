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
import io.reactivex.BackpressureStrategy
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
            Log.d(TAG, "запрос к сети")
            App.api.getCategories()
                    .map {
                        Log.d(TAG, "запрос к сети")
                        App.database.categoryDao.deleteAllCategories()
                        App.database.categoryDao.insertListCategories(it)
                        return@map it
                    }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(dataSubscription())
            App.firstOpenCategory = false
        } else {
            Log.d(TAG, "чтение из бд")
            App.database.categoryDao.getCategories()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(dataSubscription())
        }


        val resources = resources
        recyclerCategories.addItemDecoration(Decorator(
                resources.getDimension(R.dimen.margin_item)))

        return view
    }

    private fun flowableFileParse(): Flowable<List<Category?>> {
        return Flowable.create({ emitter -> emitter.onNext(Parser().getCategories(context!!)) },
                BackpressureStrategy.LATEST)
    }

    private fun dataSubscription(): DisposableSubscriber<List<Category?>> {
        return object : DisposableSubscriber<List<Category?>>() {
            override fun onComplete() {

            }

            override fun onNext(t: List<Category?>?) {
                Log.d(TAG, "размер листа ${t?.size}")
                listOfCategory = t ?: ArrayList()
                adapter.setListOfCategories(listOfCategory)
                adapter.notifyDataSetChanged()
                recyclerCategories.adapter = adapter
                group.visibility = View.VISIBLE
                categoriesBar.visibility = View.GONE
            }

            override fun onError(t: Throwable?) {
                Log.d(TAG, t.toString())
                view?.let {
                    Snackbar.make(it, t.toString(), Snackbar.LENGTH_LONG).show()
                }
                flowableFileParse()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(dataSubscription())
            }

        }
    }
}