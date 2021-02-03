package mx.com.maiktmp.habitsreminder.modules.categories.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.com.maiktmp.habitsreminder.models.Category
import mx.com.maiktmp.habitsreminder.modules.categories.data.CategoryRepository
import javax.inject.Inject
import javax.inject.Provider

class CategoryPresenterFactory @Inject constructor(
    private val repository: Provider<CategoryRepository>,
) : ViewModelProvider.Factory {
    

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CategoryPresenter(repository.get()) as T
    }
}