package mx.com.maiktmp.habitsreminder.base.di

import dagger.Component
import mx.com.maiktmp.habitsreminder.base.scopes.PerApp
import mx.com.maiktmp.habitsreminder.HabitsReminderApp
import mx.com.maiktmp.habitsreminder.modules.categories.di.CategoryComponent
import mx.com.maiktmp.habitsreminder.modules.categories.di.CategoryModule

@PerApp
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(application: HabitsReminderApp)

    fun plus(module: CategoryModule): CategoryComponent
}