package ru.nstu.ordsys.shared.user.entity

object User {

    private var id: Long = 8

    private var name: String = "Рудина С.В."

    fun getId() = id

    fun getName() = name

    fun setId(id: Long){
        this.id = id
    }

    fun setName(name: String){
        this.name = name
    }
}