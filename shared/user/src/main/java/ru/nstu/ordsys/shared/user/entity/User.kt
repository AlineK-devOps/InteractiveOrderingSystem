package ru.nstu.ordsys.shared.user.entity

object User {

    private var id: Long = 7

    private var name: String = "Артёмов В.А."

    fun getId() = id

    fun getName() = name

    fun setId(id: Long){
        this.id = id
    }

    fun setName(name: String){
        this.name = name
    }
}