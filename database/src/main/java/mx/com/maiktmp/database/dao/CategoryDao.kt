package mx.com.maiktmp.database.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import mx.com.maiktmp.database.entities.CategoryDB

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertCategory(category: CategoryDB): Completable

    @Query("SELECT * FROM category")
    fun getAll(): Single<List<CategoryDB>>

    @Delete
    fun deleteCategory(category: CategoryDB): Completable


}