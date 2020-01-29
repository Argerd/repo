package ru.argerd.repo.presenters

import android.util.Log
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.argerd.repo.App
import ru.argerd.repo.model.Event
import ru.argerd.repo.utils.Parser
import ru.argerd.repo.views.NewsView

@InjectViewState
class NewsPresenter : MvpPresenter<NewsView>() {
    private val parser = Parser()

    fun showList() {
        if (App.firstOpenEventsNews) {
            flowableForFile()
                    .map {
                        App.database.eventDao.deleteAllEvents()
                        App.database.eventDao.insertListEvents(it)
                        return@map it
                    }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(dataSubscription())
            App.firstOpenEventsNews = false
        } else {
            App.database.eventDao.getAllEvents()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(dataSubscription())
        }
    }

    private fun flowableForFile(): Flowable<List<Event>> {
        return Flowable.create({ emitter -> emitter.onNext(parser.getEvents()) },
                BackpressureStrategy.LATEST)
    }

    private fun dataSubscription(): DisposableSubscriber<List<Event>> {
        return object : DisposableSubscriber<List<Event>>() {
            override fun onComplete() {
            }

            override fun onNext(t: List<Event>?) {
                viewState.showList(t)
            }

            override fun onError(t: Throwable?) {
                Log.d("NewsFragment", "error sub ${t?.message}")
            }
        }
    }
}