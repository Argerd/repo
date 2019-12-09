package ru.argerd.repo.newsScreen

import androidx.recyclerview.widget.DiffUtil
import ru.argerd.repo.model.Event

internal class NewsDiffUtils(
        private val oldList: List<Event>,
        private val newList: List<Event>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEvent = oldList[oldItemPosition]
        val newEvent = newList[newItemPosition]
        return oldEvent.id == newEvent.id
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEvent = oldList[oldItemPosition]
        val newEvent = newList[newItemPosition]
        return oldEvent.title.equals(newEvent.title) && oldEvent.content.equals(newEvent.content)
    }
}