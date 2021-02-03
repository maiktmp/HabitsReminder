package mx.com.maiktmp.habitsreminder.modules.categories.di

import dagger.Module
import dagger.Provides
import mx.com.maiktmp.database.DBHabitsReminder
import mx.com.maiktmp.habitsreminder.modules.categories.data.CategoryRepository

@Module
class CategoryModule {

    @Provides
    fun provideCategoryRepository(): CategoryRepository {
        return CategoryRepository(DBHabitsReminder.db.categoryDao())
    }

}