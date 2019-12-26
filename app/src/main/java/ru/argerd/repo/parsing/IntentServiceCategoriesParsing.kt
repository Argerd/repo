package ru.argerd.repo.parsing

import android.app.IntentService
import android.content.Intent
import android.os.Message
import android.os.Messenger
import android.util.Log

class IntentServiceCategoriesParsing : IntentService("IntentServiceCategoriesParsing") {

    companion object {
        const val MESSENGER_EXTRA = "messenger"
        const val LIST_EXTRA = "list"
    }

    override fun onHandleIntent(intent: Intent?) {
        val listOfCategories = Parser().getCategories(application)
        Thread.sleep(5000)
        Log.d("MainActivity", "intentService ${listOfCategories.size}")
        intent?.let {
            val bundle = it.extras
            val messenger: Messenger = bundle?.get(MESSENGER_EXTRA) as Messenger
            val message = Message.obtain()
            bundle.putParcelableArrayList(LIST_EXTRA, listOfCategories)
            message.data = bundle
            try {
                messenger.send(message)
            } catch (e: Exception) {
                Log.d("MainActivity", "intentService error", e)
            }
        }
    }
}