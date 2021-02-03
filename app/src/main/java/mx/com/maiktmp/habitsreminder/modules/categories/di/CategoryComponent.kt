package mx.com.maiktmp.habitsreminder.modules.categories.di

import dagger.Subcomponent
import mx.com.maiktmp.habitsreminder.modules.categories.view.CategoriesFragment

@Subcomponent(modules = [CategoryModule::class])
interface CategoryComponent {
    fun inject(fragment: CategoriesFragment)
}