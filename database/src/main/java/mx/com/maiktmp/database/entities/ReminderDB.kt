package mx.com.maiktmp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "reminder")
data class ReminderDB(

    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,

    val name: String? = null,

    val color: Int? = null,

    val date: Date? = null,

    val time: Date? = null,



    )