package mx.com.maiktmp.database.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import mx.com.maiktmp.database.entities.CategoryDB
import mx.com.maiktmp.database.entities.ReminderDB

@Dao
interface ReminderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertReminder(reminder: ReminderDB): Completable

    @Transaction
    @Query("SELECT * FROM reminder")
    fun getAll(): Single<List<ReminderDB>>

    @Delete
    fun deleteReminder(reminder: ReminderDB): Completable

}