package ru.nstu.ordsys.order.domain.entity

enum class OrderItemStatus(val localized: String) {
    IN_QUEUE_FOR_COOKING("Ожидает приготовления"),
    IN_COOKING_PROCESS("В процессе готовки"),
    IN_QUEUE_FOR_SERVING("Ожидает подачи"),
    SERVED("Подано")
}