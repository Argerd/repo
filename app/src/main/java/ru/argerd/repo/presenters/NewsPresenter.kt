package ru.argerd.repo.presenters

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subscribers.DisposableSubscriber
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.argerd.repo.App
import ru.argerd.repo.model.pojo.Event
import ru.argerd.repo.views.NewsView

@InjectViewState
class NewsPresenter : MvpPresenter<NewsView>() {
    private val interactor = App.component.getEventsInteractor()

    fun showList() {
        if (App.firstOpenEventsNews) {
            interactor.getEventsFromNetwork()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(dataSubscription())
            App.firstOpenEventsNews = false
        } else {
            interactor.getEventsFromDatabase()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(dataSubscription())
        }
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