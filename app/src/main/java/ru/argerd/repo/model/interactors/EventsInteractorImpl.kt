package ru.argerd.repo.model.interactors

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import ru.argerd.repo.model.pojo.Event
import ru.argerd.repo.model.repository.EventsRepositoryImpl

class EventsInteractorImpl : EventsInteractor {
    private val repository = EventsRepositoryImpl()

    override fun getEventsFromNetwork(): Flowable<List<Event>> {
        return repository.getEventsFromNetwork().map {
            repository.deleteEvents()
            repository.insertListEvents(it)
            return@map it
        }.subscribeOn(Schedulers.io())
    }

    override fun getEventsFromDatabase(): Flowable<List<Event>> {
        return repository.getEventsFromDatabase().subscribeOn(Schedulers.io())
    }
}