package ru.argerd.repo.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Flowable
import ru.argerd.repo.data.model.Category

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category")
    fun getCategories(): Flowable<List<Category>>

    @Insert
    fun insertListCategories(list: List<Category>)

    @Query("delete from category")
    fun deleteAllCategories()
}