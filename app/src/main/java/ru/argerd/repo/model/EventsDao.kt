package ru.argerd.repo.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Flowable

@Dao
interface EventsDao {

    @Query("select * from event")
    fun getAllEvents(): Flowable<List<Event>>

    @Insert
    fun insertListEvents(list: List<Event>)

    @Query("delete from event")
    fun deleteAllEvents()
}