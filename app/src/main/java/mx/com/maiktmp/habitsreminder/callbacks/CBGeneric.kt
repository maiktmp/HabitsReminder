package mx.com.maiktmp.habitsreminder.callbacks

interface CBGeneric<T> {
    fun onResult(success: Boolean, result: T?)
}