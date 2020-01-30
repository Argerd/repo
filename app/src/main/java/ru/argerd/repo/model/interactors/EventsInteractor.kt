package ru.argerd.repo.model.interactors

import io.reactivex.Flowable
import ru.argerd.repo.model.pojo.Event

interface EventsInteractor {
    fun getEventsFromNetwork(): Flowable<List<Event>>

    fun getEventsFromDatabase(): Flowable<List<Event>>
}