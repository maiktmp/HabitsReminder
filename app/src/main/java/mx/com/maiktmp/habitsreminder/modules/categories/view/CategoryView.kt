package mx.com.maiktmp.habitsreminder.modules.categories.view

import mx.com.maiktmp.database.entities.CategoryDB
import mx.com.maiktmp.habitsreminder.models.Category
import mx.com.maiktmp.habitsreminder.models.GenericResponse

interface CategoryView {

    fun showProgress()

    fun hideProgress()

    fun showError(error: String)

    fun showSuccess(gr: GenericResponse<Any>)

    fun listData(list: ArrayList<CategoryDB>)

    fun deleteCategoryOn(categoryId: Long)


}