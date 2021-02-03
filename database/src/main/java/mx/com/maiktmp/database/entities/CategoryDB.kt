package mx.com.maiktmp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryDB(

    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,

    val name: String? = null,

    val color: Int? = null,


    )