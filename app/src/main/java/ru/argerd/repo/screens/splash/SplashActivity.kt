package ru.argerd.repo.screens.splash

import android.content.Intent
import android.os.Bundle
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import ru.argerd.repo.R
import ru.argerd.repo.screens.authorization.AuthorizationActivity

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