package ru.argerd.repo.ui

import android.content.Intent
import android.os.Bundle
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import ru.argerd.repo.R
import ru.argerd.repo.presenters.SplashPresenter
import ru.argerd.repo.views.SplashView

class SplashActivity : MvpAppCompatActivity(), SplashView {
    @InjectPresenter
    lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter.moveToMainActivityWithTimer()
    }

    override fun moveToMainActivity() {
        startActivity(Intent(this@SplashActivity,
                AuthorizationActivity::class.java))
    }
}