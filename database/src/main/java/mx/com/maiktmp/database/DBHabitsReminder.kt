package mx.com.maiktmp.database

import android.content.Context
import androidx.room.*
import mx.com.maiktmp.database.converters.Converters
import mx.com.maiktmp.database.dao.CategoryDao
import mx.com.maiktmp.database.entities.CategoryDB

@Database(
    entities = [CategoryDB::class],
    version = 4,
    exportSchema = false
)
@TypeConverters(Converters::class)
public abstract class DBHabitsReminder : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

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