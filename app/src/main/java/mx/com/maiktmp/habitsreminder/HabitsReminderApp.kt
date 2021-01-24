package mx.com.maiktmp.habitsreminder

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy


class HabitsReminderApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG_MODE) {
            setupLogger()
            Logger.i("Logger Ready!!")
        }
    }


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
}