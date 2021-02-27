package mx.com.maiktmp.habitsreminder.modules.categories.data

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import mx.com.maiktmp.database.dao.CategoryDao
import mx.com.maiktmp.database.entities.CategoryDB
import mx.com.maiktmp.habitsreminder.models.GenericResponse
import kotlin.collections.ArrayList

class CategoryRepository(private val categoryDao: CategoryDao) {

    fun insert(category: CategoryDB, cb: (res: GenericResponse<Any>) -> Unit): Disposable {
        val gr = GenericResponse<Any>()
        return categoryDao
            .upsertCategory(category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                cb(gr.apply {
                    success = true
                })
            }, {
                cb(gr.apply {
                    message = "No se pudo crear una nueva categoría, intente más tarde"
                })
            })
    }

    fun getAll(cb: (gr: GenericResponse<ArrayList<CategoryDB>>) -> Unit): Disposable {
        val gr = GenericResponse<ArrayList<CategoryDB>>()
        return categoryDao
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                cb(gr.apply {
                    success = true
                    data = ArrayList(it)
                })
            }, {
                cb(gr.apply {
                    message = "Ocurrió un error al obtener las categorias"
                })
            }
            )
    }

    fun deleteCategory(category: CategoryDB, cb: (GenericResponse<Any>) -> Unit): Disposable {
        val gr = GenericResponse<Any>()
        return categoryDao
            .deleteCategory(category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                cb(gr.apply {
                    success = true
                })
            }, {
                cb(gr.apply {
                    message = "Ocurrió un error al eliminar la categoría"
                })
            }
            )
    }

}