package mx.com.maiktmp.habitsreminder.ui_modules.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import mx.com.maiktmp.habitsreminder.R
import mx.com.maiktmp.habitsreminder.databinding.ActivityLoginBinding
import mx.com.maiktmp.habitsreminder.ui_modules.auth.mvp.LoginPresenter
import mx.com.maiktmp.habitsreminder.ui_modules.auth.mvp.LoginView
import mx.com.maiktmp.habitsreminder.ui_modules.models.User

class LoginActivity : AppCompatActivity(), LoginView {

    private val vBind: ActivityLoginBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    private val presenter = LoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vBind.vBackground
        vBind.btnLogin.setOnClickListener {
            presenter.attemptLogin(
                User("maiktmp@gmail.com","pruebsa")
            )
        }
    }

    override fun attemptLogin() {

    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

}