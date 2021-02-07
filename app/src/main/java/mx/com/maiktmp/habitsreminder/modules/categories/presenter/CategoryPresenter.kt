package mx.com.maiktmp.habitsreminder.modules.categories.presenter

import mx.com.maiktmp.database.entities.CategoryDB
import mx.com.maiktmp.habitsreminder.base.presenter.BasePresenter
import mx.com.maiktmp.habitsreminder.modules.categories.data.CategoryRepository
import mx.com.maiktmp.habitsreminder.modules.categories.view.CategoryView
import javax.inject.Inject


class CategoryPresenter @Inject constructor(
    private val repository: CategoryRepository,
) : BasePresenter<CategoryView>() {

    fun create(category: CategoryDB) {
        repository.insert(category) {
            if (it.success) {
                view()?.showSuccess(it)
                getAllCategories()
            } else {
                view()?.showError(it.message!!)
            }
        }.autoClear()
    }


    fun getAllCategories() {
        view()?.showProgress()
        repository.getAll {
            view()?.hideProgress()
            if (it.success) {
                view()?.listData(it.data!!)
            } else {
                view()?.showError(it.message!!)
            }
        }.autoClear()
    }

    fun deleteCategory(category: CategoryDB) {
        view()?.showProgress()
        repository.deleteCategory(category) {
            view()?.hideProgress()
            if (it.success) {
                view()?.deleteCategoryOn(category.id!!)
            } else {
                view()?.showError(it.message!!)
            }
        }.autoClear()
    }
}