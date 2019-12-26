package ru.argerd.repo.parsing

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import ru.argerd.repo.R
import ru.argerd.repo.model.Category

class AsyncTaskCategoriesParsing(
        val context: Context?,
        private val callbackList: () -> Unit,
        private val callbackBar: () -> Unit
) : AsyncTask<Void, Void, ArrayList<Category>?>() {
    override fun doInBackground(vararg params: Void?): ArrayList<Category>? {
        val parser = Parser()
        context?.let {
            val listOfCategories: ArrayList<Category>?
            listOfCategories = parser.getCategories(it)
            Thread.sleep(5000)
            Log.d("MainActivity", "asycn ${listOfCategories.size}")
            return listOfCategories
        }
        return null
    }

    override fun onPostExecute(result: ArrayList<Category>?) {
        super.onPostExecute(result)
        Toast.makeText(context, R.string.load_successful, Toast.LENGTH_LONG).show()
        callbackBar()
        callbackList()
    }
}