package ru.argerd.repo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.disposables.CompositeDisposable

class AuthorizationActivity : AppCompatActivity() {
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button
    private lateinit var toolbar: Toolbar

    private var emailLength = 0
    private var passwordLength = 0

    private var compositeDisposable = CompositeDisposable()

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
        compositeDisposable.add(email.textChanges().subscribe { text -> emailLength = text.length })
        compositeDisposable
                .add(password.textChanges().subscribe { text -> passwordLength = text.length })

        compositeDisposable.add(loginButton.clicks().subscribe {
            if (passwordLength > 5 && emailLength > 5) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Snackbar.make(loginButton,
                        "E-mail или пароль слишком мал!", Snackbar.LENGTH_LONG).show()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}