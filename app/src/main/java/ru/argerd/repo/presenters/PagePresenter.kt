package ru.argerd.repo.presenters

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.argerd.repo.model.Event
import ru.argerd.repo.parsing.Parser
import ru.argerd.repo.views.PageView
import java.util.*

@InjectViewState
class PagePresenter : MvpPresenter<PageView>() {
    private lateinit var disposable: Disposable

    fun onSearch(search: Observable<CharSequence>) {
        search.switchMap { seq ->
            val input = seq.toString()
            return@switchMap Observable.just(getSearchResult(input))
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchSubscriber())
    }

    private fun getSearchResult(input: String): List<String?>? {
        val events: List<Event> = Parser().getEvents()
        val result: MutableList<String?> = ArrayList()
        for (i in events.indices) {
            events[i].name?.let {
                if (it.toLowerCase().contains(input)) {
                    result.add(events[i].name)
                }
            }
        }
        return result
    }

    private fun searchSubscriber(): Observer<List<String?>?> {
        return object : Observer<List<String?>?> {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
                disposable = d
            }

            override fun onError(e: Throwable) {
            }

            override fun onNext(result: List<String?>) {
                if (result.isEmpty()) {
                    viewState.skipListOfResults()
                } else {
                    viewState.showListOfResults(result)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}