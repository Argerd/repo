package ru.argerd.repo.parsing

import android.content.Context
import android.content.SharedPreferences
import android.os.Handler
import android.os.Looper
import android.util.Log
import ru.argerd.repo.model.Event
import java.util.concurrent.Executor
import java.util.concurrent.Executors

private const val FILTER_SETTINGS = "filterSettings"

class ExecutorEventsParsing(
        private val context: Context?,
        private val callback: (list: ArrayList<Event>) -> Unit
) {
    private val executor: Executor = Executors.newSingleThreadExecutor()
    private val handler = Handler(Looper.getMainLooper())

    private var settings: List<String>? = null
    private var validEvents: ArrayList<Event> = ArrayList()
    private lateinit var listEvents: ArrayList<Event>
    private val parser = Parser()
    private var sharedPreferences: SharedPreferences? = null

    fun execute() {
        executor.execute {
            sharedPreferences = context?.getSharedPreferences(FILTER_SETTINGS, Context.MODE_PRIVATE)

            settings = sharedPreferences?.all?.keys?.distinct()

            context?.let {
                listEvents = parser.getEvents(it)
            }

            settings?.let { settings ->
                if (settings.isEmpty()) {
                    validEvents = listEvents
                } else {
                    validEvents.clear()
                    for (i in listEvents.indices) {
                        listEvents[i].categories?.let { category ->
                            var counter = 0
                            for (j in category.indices) {
                                if (settings.contains(category[j]?.name)) {
                                    counter++
                                }
                                if (counter == settings.size) {
                                    validEvents.add(listEvents[i])
                                    counter = 0
                                }
                            }
                        }
                    }
                }
            }
            Thread.sleep(5000)
            Log.d("NewsScreen", "async valid events size ${validEvents.size}")
            handler.post { callback(validEvents) }
        }
    }
}