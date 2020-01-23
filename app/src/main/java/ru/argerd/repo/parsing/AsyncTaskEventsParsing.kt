package ru.argerd.repo.parsing

import android.content.Context
import android.content.SharedPreferences
import android.os.AsyncTask
import android.util.Log
import ru.argerd.repo.model.Event

private const val FILTER_SETTINGS = "filterSettings"

class AsyncTaskEventsParsing(
        private val context: Context?,
        private val callback: (MutableList<Event>) -> Unit
) : AsyncTask<Void, Void, MutableList<Event>>() {
    /*private var settings: List<String>? = null
    private var validEvents: MutableList<Event> = mutableListOf()*/
    private lateinit var listEvents: MutableList<Event>
    private val parser = Parser()
    /*private var sharedPreferences: SharedPreferences? = null*/

    override fun doInBackground(vararg params: Void?): MutableList<Event> {
        /*sharedPreferences = context?.getSharedPreferences(FILTER_SETTINGS, Context.MODE_PRIVATE)

        settings = sharedPreferences?.all?.keys?.distinct()*/

        /*context?.let {
            listEvents = parser.getEvents(it)
        }*/

        /*settings?.let { settings ->
            if (settings.isEmpty()) {
                validEvents = listEvents.toMutableList()
            } else {
                validEvents.clear()
                for (i in listEvents.indices) {
                    listEvents[i].category?.let { category ->
                        var counter = 0
                        for (j in category.indices) {
                            if (settings.contains(category)) {
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
        Log.d("NewsScreen", "async valid events size ${validEvents.size}")*/
        return listEvents
    }

    override fun onPostExecute(result: MutableList<Event>?) {
        super.onPostExecute(result)
        result?.let { callback(it) }
    }

}