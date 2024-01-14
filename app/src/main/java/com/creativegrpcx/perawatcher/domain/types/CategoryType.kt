package com.creativegrpcx.perawatcher.domain.types

enum class CategoryType(val text: String) {
    Appliances("Appliances"),
    Bills("Bills"),
    BeautyAndPersonalCare("Beauty And Personal Care"),
    Electronics("Electronics"),
    Food("Food"),
    Furniture("Furniture"),
    Fashion("Fashion"),
    Health("Health"),
    Hobbies("Hobbies"),
    PetSupplies("Pet"),
    Subscriptions("Subscriptions"),
    Others("Others")
}

/**
 * Returns `true` if enum T contains an entry with the specified name.
 */
inline fun <reified T : Enum<T>> enumContains(name: String): Boolean {
    return T::class.java.enumConstants?.any { it.name == name} ?: false
}

