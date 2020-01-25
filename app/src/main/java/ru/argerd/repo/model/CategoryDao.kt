package ru.argerd.repo.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Flowable

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category")
    fun getCategories(): Flowable<List<Category>>

    @Insert
    fun insertListCategories(list: List<Category>)

    @Query("delete from category")
    fun deleteAllCategories()
}