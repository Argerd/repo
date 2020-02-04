package ru.argerd.repo.domain.repositories

import io.reactivex.Flowable
import ru.argerd.repo.data.model.Event

interface EventsRepository {
    /**
     * Events будут парситься из файла, так как обращение к ресурсу в Интернете невыполнимо
     */
    fun getEventsFromNetwork(): Flowable<List<Event>>

    fun getEventsFromDatabase(): Flowable<List<Event>>

    fun deleteEvents()

    fun insertListEvents(list: List<Event>)
}