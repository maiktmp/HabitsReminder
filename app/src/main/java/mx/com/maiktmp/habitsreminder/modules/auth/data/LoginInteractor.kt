package mx.com.maiktmp.habitsreminder.modules.auth.data

import mx.com.maiktmp.habitsreminder.models.GenericResponse
import mx.com.maiktmp.habitsreminder.models.User

object LoginInteractor {

    fun attemptFirebaseLogin(user: User, cb: (result: GenericResponse<User>) -> Unit) {
        FBRepository.attemptLogin(user, cb)
    }

}