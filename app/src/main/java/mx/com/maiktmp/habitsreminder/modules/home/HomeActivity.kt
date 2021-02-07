package mx.com.maiktmp.habitsreminder.modules.home

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import mx.com.maiktmp.database.entities.CategoryDB
import mx.com.maiktmp.habitsreminder.R
import mx.com.maiktmp.habitsreminder.databinding.ActivityHomeBinding
import mx.com.maiktmp.habitsreminder.modules.CustomActivity
import mx.com.maiktmp.habitsreminder.modules.categories.view.CategoriesFragment
import mx.com.maiktmp.habitsreminder.modules.categories.view.dialogs.FormCategoryDialog
import mx.com.maiktmp.habitsreminder.utils.Codes
import mx.com.maiktmp.habitsreminder.utils.GenericDialog


class HomeActivity : CustomActivity(), CategoriesFragment.CategoryListener {

    val vBind: ActivityHomeBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_home)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.toolbarBinding = vBind.iToolbar
        super.setupToolbar(getString(R.string.categories))

        setupAddBtn()
        setupMainFragment()
    }


    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        if (fragment is CategoriesFragment) {
            fragment.categoryListener = this
            val searchView = vBind.bottomAppBar.menu.findItem(R.id.search).actionView as androidx.appcompat.widget.SearchView
            searchView.setOnQueryTextListener(fragment)
        }
    }

    private fun setupMainFragment() {
        if (supportFragmentManager.findFragmentByTag(Codes.FRAGMENT_CATEGORIES) != null) return
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fl_root, CategoriesFragment(), Codes.FRAGMENT_CATEGORIES)
            .commit()
    }

    private fun setupAddBtn() {
        vBind.btnAdd.setOnClickListener {
            displayCategoryDialog()
        }
    }


    private fun displayCategoryDialog(category: CategoryDB? = null, position: Int = 0) {
        val fragment =
            supportFragmentManager.findFragmentByTag(Codes.FRAGMENT_CATEGORIES) as CategoriesFragment

        FormCategoryDialog.newInstance(category).apply {
            show(supportFragmentManager, FormCategoryDialog.TAG)
            onClickDoneButton = { id, name, color ->
                fragment.createCategory(id, name, color)
            }
            category?.let { _ ->
                onClickDeleteButton = { id ->
                    this.dismiss()
                    GenericDialog.newInstance(
                        "¿Desea eliminar la categoria?",
                        "Se eliminará con todo y su contenido"
                    ).apply {
                        show(supportFragmentManager, GenericDialog.TAG)
                        this.setupOkButton({
                            this.dismiss()
                            fragment.deleteCategory(id)
                        })
                        this.setupCancelButton({
                            this.dismiss()
                        })
                    }
                }
            }
        }
    }

    /**
     * @see CategoriesFragment.categoryListener
     */
    override fun onLongClickCategory(category: CategoryDB, position: Int) {
        displayCategoryDialog(category, position)
    }
}