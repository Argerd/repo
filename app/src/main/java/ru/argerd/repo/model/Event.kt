package ru.argerd.repo.model

class Event {
    var id: Int? = null
    var title: String? = null
    var date: String? = null
    var nameOfOrganozation: String? = null
    var address: String? = null
    var phones: String? = null
    var content: String? = null
    var categories: List<Category?>? = null
}