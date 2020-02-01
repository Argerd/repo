package ru.argerd.repo.model.repository

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import ru.argerd.repo.model.database.EventsDao
import ru.argerd.repo.model.pojo.Event
import ru.argerd.repo.utils.Parser

class EventsRepositoryImpl(
        private val parser: Parser,
        private val database: EventsDao
) : EventsRepository {

    /**
     * Events будут парситься из файла, так как обращение к ресурсу в Интернете невыполнимо
     */
    override fun getEventsFromNetwork(): Flowable<List<Event>> {
        return Flowable.create({ emitter -> emitter.onNext(parser.getEvents()) },
                BackpressureStrategy.LATEST)
    }

    override fun getEventsFromDatabase(): Flowable<List<Event>> {
        return database.getAllEvents()
    }

    override fun deleteEvents() {
        database.deleteAllEvents()
    }

    override fun insertListEvents(list: List<Event>) {
        database.insertListEvents(list)
    }
}