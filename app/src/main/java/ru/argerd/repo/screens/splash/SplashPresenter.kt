package ru.argerd.repo.screens.splash

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.argerd.repo.screens.splash.SplashView
import java.util.concurrent.TimeUnit

@InjectViewState
class SplashPresenter : MvpPresenter<SplashView>() {
    private lateinit var disposable: Disposable

    fun moveToMainActivityWithTimer() {
        disposable = Completable.timer(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { viewState.moveToMainActivity() }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}