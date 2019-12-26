package ru.argerd.repo.parsing

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import ru.argerd.repo.model.Category
import java.util.concurrent.Executor
import java.util.concurrent.Executors

internal class ExecutorCategoriesParsing(
        private val context: Context?,
        private val callback: (list: ArrayList<Category>?) -> Unit
) {
    private val executor: Executor = Executors.newSingleThreadExecutor()
    private val handler = Handler(Looper.getMainLooper())

    fun execute() {
        executor.execute {
            context?.let {
                val listOfCategories = Parser().getCategories(it)
                Thread.sleep(5000)
                Log.d("MainActivity", "executor ${listOfCategories.size}")
                handler.post {
                    callback(listOfCategories)
                }
            }
        }
    }
}