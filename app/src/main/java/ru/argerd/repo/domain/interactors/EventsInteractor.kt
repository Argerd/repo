package ru.argerd.repo.domain.interactors

import io.reactivex.Flowable
import ru.argerd.repo.data.model.Event

interface EventsInteractor {
    fun getEventsFromNetwork(): Flowable<List<Event>>

    fun getEventsFromDatabase(): Flowable<List<Event>>
}