package ru.argerd.repo.parsing

import android.app.IntentService
import android.content.Intent
import android.os.Message
import android.os.Messenger
import android.util.Log
import ru.argerd.repo.model.Event

private const val FILTER_SETTINGS = "filterSettings"

class IntentServiceEventsParsing : IntentService("IntentServiceEventsParsing") {

    companion object {
        const val MESSENGER_EXTRA = "messenger"
        const val LIST_EXTRA = "list"
    }

    /*private var settings: List<String>? = null
    private var validEvents: ArrayList<Event> = ArrayList()*/
    private lateinit var listEvents: ArrayList<Event?>
    private val parser = Parser()
    // private var sharedPreferences: SharedPreferences? = null

    override fun onHandleIntent(intent: Intent?) {
        /*sharedPreferences = application.getSharedPreferences(FILTER_SETTINGS, Context.MODE_PRIVATE)

        settings = sharedPreferences?.all?.keys?.distinct()*/

        // listEvents = parser.getEvents(application)

        /*settings?.let { settings ->
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
        }*/
        //listEvents = parser.getEvents(application)
        //Thread.sleep(5000)
        Log.d("NewsScreen", "async valid events size ${listEvents.size}")
        intent?.let {
            val bundle = it.extras
            val messenger: Messenger = bundle?.get(MESSENGER_EXTRA) as Messenger
            val message = Message.obtain()
            bundle.putParcelableArrayList(LIST_EXTRA, listEvents)
            message.data = bundle
            try {
                messenger.send(message)
            } catch (e: Exception) {
                Log.d("MainActivity", "intentService error", e)
            }
        }
    }
}