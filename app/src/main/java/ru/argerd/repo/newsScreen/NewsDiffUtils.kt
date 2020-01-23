package ru.argerd.repo.newsScreen

import androidx.recyclerview.widget.DiffUtil
import ru.argerd.repo.model.Event

internal class NewsDiffUtils(
        private val oldList: ArrayList<Event>?,
        private val newList: ArrayList<Event>?
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        if (oldList != null && newList != null) {
            val oldEvent = oldList[oldItemPosition]
            val newEvent = newList[newItemPosition]
            return oldEvent.id == newEvent.id
        }
        return false
    }

    override fun getOldListSize(): Int {
        return oldList?.size ?: 0
    }

    override fun getNewListSize(): Int {
        return newList?.size ?: 0
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        if (oldList != null && newList != null) {
            val oldEvent = oldList[oldItemPosition]
            val newEvent = newList[newItemPosition]
            return oldEvent.name == newEvent.name && oldEvent.description == newEvent.description
        }
        return false
    }
}