package ru.argerd.repo.model.repository

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import ru.argerd.repo.App
import ru.argerd.repo.di.DaggerAppComponent
import ru.argerd.repo.model.pojo.Event

class EventsRepositoryImpl : EventsRepository {
    private val parser = DaggerAppComponent.create().getParser()

    /**
     * Events будут парситься из файла, так как обращение к ресурсу в Интернете невыполнимо
     */
    override fun getEventsFromNetwork(): Flowable<List<Event>> {
        return Flowable.create({ emitter -> emitter.onNext(parser.getEvents()) },
                BackpressureStrategy.LATEST)
    }

    override fun getEventsFromDatabase(): Flowable<List<Event>> {
        return App.database.eventDao.getAllEvents()
    }

    override fun deleteEvents() {
        App.database.eventDao.deleteAllEvents()
    }

    override fun insertListEvents(list: List<Event>) {
        App.database.eventDao.insertListEvents(list)
    }
}