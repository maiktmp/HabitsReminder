package mx.com.maiktmp.habitsreminder.modules.auth.data

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.orhanobut.logger.Logger
import mx.com.maiktmp.habitsreminder.models.GenericResponse
import mx.com.maiktmp.habitsreminder.models.User

object FBRepository {

    private val auth = Firebase.auth

    fun attemptLogin(user: User, cb: (result: GenericResponse<User>) -> Unit) {
        val gr = GenericResponse<User>()
        auth.signInWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    gr.apply {
                        message = "Usuario o contraseña incorrectos"
                    }
                    Logger.w("signInWithEmail:failure:${task.exception}")

                } else {
                    val user = auth.currentUser
                    gr.apply {
                        success = true
                    }
                }
                cb(gr)
            }
    }
}