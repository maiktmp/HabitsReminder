package mx.com.maiktmp.habitsreminder.modules.auth.presenter

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import mx.com.maiktmp.habitsreminder.modules.auth.data.LoginInteractor
import mx.com.maiktmp.habitsreminder.modules.auth.view.LoginView
import mx.com.maiktmp.habitsreminder.models.GenericResponse
import mx.com.maiktmp.habitsreminder.models.User

class LoginPresenter(private val view: LoginView) {

    fun attemptLogin(user: User) {
        view.showProgress()
        LoginInteractor.attemptFirebaseLogin(user) {
            view.hideProgress()
            if (it.success) {
                view.LoginSuccessful()
            } else {
                view.LoginError(it)
            }
        }
    }

    fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            view.LoginSuccessful()
        } catch (e: ApiException) {
            view.LoginError(GenericResponse(message = " No se pudo completar el inicio de sesión  "))
        }
    }


}