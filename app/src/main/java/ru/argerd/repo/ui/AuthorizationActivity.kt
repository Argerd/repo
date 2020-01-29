package ru.argerd.repo.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import ru.argerd.repo.R
import ru.argerd.repo.presenters.AuthorizationPresenter
import ru.argerd.repo.views.AuthorizationView

class AuthorizationActivity : MvpAppCompatActivity(), AuthorizationView {
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button
    private lateinit var toolbar: Toolbar

    @InjectPresenter
    lateinit var presenter: AuthorizationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)
        toolbar = findViewById(R.id.authorizationToolbar)
        toolbar.apply {
            setNavigationIcon(R.drawable.ic_back)
            setNavigationOnClickListener { onBackPressed() }
        }

        email = findViewById(R.id.emailEditText)
        password = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
    }

    override fun onResume() {
        super.onResume()

        presenter.onEmailInput(email.textChanges()
                .switchMap { Observable.just(it.toString()) })
        presenter.onPasswordInput(password.textChanges()
                .switchMap { Observable.just(it.toString()) })

        loginButton.setOnClickListener {
            presenter.onButtonClicked()
        }
    }

    override fun moveToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun showWarning() {
        Snackbar.make(loginButton,
                "E-mail или пароль слишком мал!", Snackbar.LENGTH_LONG).show()
    }
}