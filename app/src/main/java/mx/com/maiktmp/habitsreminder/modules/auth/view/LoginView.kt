package mx.com.maiktmp.habitsreminder.modules.auth.view

import mx.com.maiktmp.habitsreminder.models.GenericResponse
import mx.com.maiktmp.habitsreminder.models.User

interface LoginView {

    fun LoginSuccessful()

    fun LoginError(gr: GenericResponse<User>)

    fun showProgress()

    fun hideProgress()


}