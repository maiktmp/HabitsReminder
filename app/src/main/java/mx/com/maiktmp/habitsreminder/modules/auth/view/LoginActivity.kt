package mx.com.maiktmp.habitsreminder.modules.auth.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.github.razir.progressbutton.attachTextChangeAnimator
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import mx.com.maiktmp.habitsreminder.R
import mx.com.maiktmp.habitsreminder.databinding.ActivityLoginBinding
import mx.com.maiktmp.habitsreminder.modules.auth.presenter.LoginPresenter
import mx.com.maiktmp.habitsreminder.models.GenericResponse
import mx.com.maiktmp.habitsreminder.models.User
import mx.com.maiktmp.habitsreminder.utils.Codes
import mx.com.maiktmp.habitsreminder.utils.Extensions.displayToast
import mx.com.maiktmp.habitsreminder.utils.Extensions.required
import mx.com.maiktmp.habitsreminder.utils.Extensions.validateMail


class LoginActivity : AppCompatActivity(), LoginView {

    private val vBind: ActivityLoginBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    private val presenter = LoginPresenter(this)
    private val user = User()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vBind.user = user
        setupSigInButton()
        setupSigInGoogle()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Codes.REQUEST_GOOGLE_SIG_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            presenter.handleSignInResult(task)
        }
    }

    private fun setupSigInButton() {
        bindProgressButton(vBind.btnLogin)
        vBind.btnLogin.attachTextChangeAnimator()
        vBind.btnLogin.setOnClickListener {
            if (validate()) {
                presenter.attemptLogin(user)
            }
        }
    }

    private fun setupSigInGoogle() {
        vBind.btnLoginGoogle.setOnClickListener {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            val googleSignInClient = GoogleSignIn.getClient(this, gso)
            val signInIntent: Intent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, Codes.REQUEST_GOOGLE_SIG_IN)
        }
    }

    private fun validate(): Boolean {
        return vBind.tilEmail.required() &&
                vBind.tilEmail.validateMail() &&
                vBind.tilPassword.required()

    }

    override fun LoginSuccessful() {

    }

    override fun LoginError(gr: GenericResponse<User>) {
        displayToast(gr.message, Toast.LENGTH_SHORT)
    }


    override fun showProgress() {
        vBind.btnLogin.showProgress {
            buttonTextRes = R.string.loading
            progressColor = Color.WHITE
        }
    }

    override fun hideProgress() {
        vBind.btnLogin.hideProgress(R.string.log_in)
    }

}