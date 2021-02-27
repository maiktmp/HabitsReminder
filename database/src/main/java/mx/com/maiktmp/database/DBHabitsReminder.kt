package mx.com.maiktmp.database

import android.content.Context
import androidx.room.*
import mx.com.maiktmp.database.converters.Converters
import mx.com.maiktmp.database.dao.CategoryDao
import mx.com.maiktmp.database.dao.ReminderDao
import mx.com.maiktmp.database.entities.CategoryDB
import mx.com.maiktmp.database.entities.ReminderDB

@Database(
    entities = [
        CategoryDB::class,
        ReminderDB::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
public abstract class DBHabitsReminder : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun reminderDao(): ReminderDao

    companion object {

        @Volatile
        lateinit var db: DBHabitsReminder

        public fun createDatabase(context: Context) {
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DBHabitsReminder::class.java,
                    "HabitsReminder.db"
                ).fallbackToDestructiveMigration().build()
                db = instance
                instance
            }
        }
    }

}