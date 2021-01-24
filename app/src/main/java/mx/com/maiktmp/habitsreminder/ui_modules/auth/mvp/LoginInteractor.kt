package mx.com.maiktmp.habitsreminder.ui_modules.auth.mvp

import mx.com.maiktmp.habitsreminder.ui_modules.auth.general.FBRepository
import mx.com.maiktmp.habitsreminder.ui_modules.models.User

object LoginInteractor {

    fun attemptFirebaseLogin(user: User) {
        FBRepository.attemptLogin(user)
    }

}