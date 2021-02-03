package mx.com.maiktmp.habitsreminder.base.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import mx.com.maiktmp.habitsreminder.base.di.qualifiers.ApplicationContext

@Module
class ApplicationModule(private val application: Application) {
    @Provides
    @ApplicationContext
    fun provideContext(): Context = application
}