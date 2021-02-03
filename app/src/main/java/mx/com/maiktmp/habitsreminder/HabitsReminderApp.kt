package mx.com.maiktmp.habitsreminder

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import mx.com.maiktmp.database.DBHabitsReminder
import mx.com.maiktmp.habitsreminder.base.di.ApplicationComponent
import mx.com.maiktmp.habitsreminder.base.di.ApplicationModule
import mx.com.maiktmp.habitsreminder.base.di.DaggerApplicationComponent


class HabitsReminderApp : Application() {

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG_MODE) {
            setupLogger()
            Logger.i("Logger Ready!!")
        }

        initDatabase()
        initDagger()
    }

    private fun initDagger() {
        applicationComponent =
            DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
        applicationComponent.inject(this)
    }

    fun getComponent() = applicationComponent



    private fun setupLogger() {
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .tag("Habits Reminder")
            .build()

        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG_MODE
            }
        })
    }


    private fun initDatabase() {
        DBHabitsReminder.createDatabase(this)
    }
}