package mx.com.maiktmp.habitsreminder.modules.categories.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import mx.com.maiktmp.database.entities.CategoryDB
import mx.com.maiktmp.habitsreminder.HabitsReminderApp
import mx.com.maiktmp.habitsreminder.databinding.FragmentCategoriesBinding
import mx.com.maiktmp.habitsreminder.models.Category
import mx.com.maiktmp.habitsreminder.models.GenericResponse
import mx.com.maiktmp.habitsreminder.modules.categories.di.CategoryModule
import mx.com.maiktmp.habitsreminder.modules.categories.presenter.CategoryPresenter
import mx.com.maiktmp.habitsreminder.modules.categories.presenter.CategoryPresenterFactory
import mx.com.maiktmp.habitsreminder.modules.categories.view.adapter.CategoryAdapter
import mx.com.maiktmp.habitsreminder.utils.Extensions.displayToast
import mx.com.maiktmp.habitsreminder.utils.Extensions.hideView
import mx.com.maiktmp.habitsreminder.utils.Extensions.showView
import javax.inject.Inject

class CategoriesFragment : Fragment(), CategoryView {

    lateinit var vBind: FragmentCategoriesBinding
    lateinit var adapter: CategoryAdapter
    var categoryListener: CategoryListener? = null

    @Inject
    lateinit var categoryPresenterFactory: CategoryPresenterFactory
    private lateinit var presenter: CategoryPresenter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vBind = FragmentCategoriesBinding.inflate(inflater, container, false)

        injectDependencies()
        setupPresenter()
        setupRecyclerView()
        setupAdapter()

        presenter.getAllCategories()
        return vBind.root
    }

    fun setupPresenter() {
        presenter = ViewModelProvider(this, categoryPresenterFactory)
            .get(CategoryPresenter::class.java)

        presenter.attachView(this, lifecycle)
    }

    fun createCategory(id: Long?, name: String, color: Int) {
        presenter.create(CategoryDB(id, name, color))
    }

    fun deleteCategory(id: Long?, index: Int) {
        presenter.deleteCategory(CategoryDB(id), index)
    }

    private fun setupAdapter() {
        adapter = CategoryAdapter(arrayListOf(), categoryListener)
        vBind.rvCategories.adapter = adapter
    }

    private fun setupRecyclerView() {
        vBind.rvCategories.layoutManager = GridLayoutManager(context, 2)
        vBind.rvCategories.itemAnimator = DefaultItemAnimator()
    }

    override fun showProgress() {
        vBind.pbLoading.showView()
    }

    override fun hideProgress() {
        vBind.pbLoading.hideView()
    }


    override fun showError(error: String) {
        context?.displayToast(error, Toast.LENGTH_SHORT)
    }

    override fun listData(list: ArrayList<CategoryDB>) {
        if (list.isEmpty()) {
            vBind.lblEmpty.showView()
            return
        }
        vBind.lblEmpty.hideView()
        adapter.categories = list
        adapter.notifyDataSetChanged()
    }

    override fun deleteCategoryOn(index: Int) {
        adapter.categories.removeAt(index)
        adapter.notifyItemRemoved(index)
        if (adapter.categories.isEmpty()) {
            vBind.lblEmpty.showView()
        } else {
            vBind.lblEmpty.hideView()
        }
    }

    override fun showSuccess(gr: GenericResponse<Any>) {

    }


    private fun injectDependencies() {
        val app = activity?.application as HabitsReminderApp
        app.getComponent()
            .plus(CategoryModule())
            .inject(this)
    }

    interface CategoryListener {
        fun onLongClickCategory(category: CategoryDB, index: Int)
    }

}