package mx.com.maiktmp.habitsreminder.models

data class GenericResponse<T>(
    var success: Boolean = false,
    var message: String? = "",
    var data: T? = null,
    var code: Int? = 0,
)
