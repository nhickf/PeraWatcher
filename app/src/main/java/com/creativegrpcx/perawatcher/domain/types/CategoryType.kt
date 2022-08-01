package com.creativegrpcx.perawatcher.domain.types

enum class CategoryType(val id : Int) {
    Appliance(1),
    Bills(2),
    BeautyAndPersonalCare(3),
    ComputerAndITAccessories(4),
    Electronics(5),
    FoodAndGroceries(6),
    Furniture(7),
    Fashion(8),
    Health(9),
    HobbiesAndLeisure(10),
    PetSupplies(11),
    Subscription(13),
    Others(0),
}

/**
 * Returns `true` if enum T contains an entry with the specified name.
 */
inline fun <reified T : Enum<T>> enumContains(name: String): Boolean {
    return T::class.java.enumConstants?.any { it.name == name} ?: false
}
