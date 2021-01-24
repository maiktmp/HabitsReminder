package mx.com.maiktmp.habitsreminder.ui_modules.auth.general

import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.orhanobut.logger.Logger
import mx.com.maiktmp.habitsreminder.ui_modules.models.User

object FBRepository {

    private val auth = Firebase.auth

    fun attemptLogin(user: User) {
        auth.signInWithEmailAndPassword(user.email!!, user.password!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Logger.d("signInWithEmail:success")
                    val user = auth.currentUser
                    Logger.i(user.toString())
                } else {
                    Logger.w("signInWithEmail:failure:${task.exception}")
                }

            }
    }
}