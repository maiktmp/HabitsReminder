package mx.com.maiktmp.habitsreminder.ui_modules.auth.mvp

import mx.com.maiktmp.habitsreminder.ui_modules.models.User

class LoginPresenter(val view: LoginView) {

    fun attemptLogin(user: User) {
        LoginInteractor.attemptFirebaseLogin(user)
    }
}