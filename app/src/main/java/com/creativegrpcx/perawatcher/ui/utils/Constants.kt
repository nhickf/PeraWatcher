package com.creativegrpcx.perawatcher.ui.utils

import com.creativegrpcx.perawatcher.R
import com.creativegrpcx.perawatcher.domain.types.CategoryType
import com.creativegrpcx.perawatcher.ui.nav.ScreenRoute

object Constants {

    val ScreenRoutes = listOf(
        ScreenRoute( route = "dashboard", name = "dashboard", isSelected = true),
        ScreenRoute( route = "statistics", name = "statistics"),
        ScreenRoute( route = "history", name = "history"),
        ScreenRoute( route = "wallet", name = "wallet"),
    )

    fun CategoryIcon(categoryType: CategoryType) : Int{
        return when(categoryType){
            CategoryType.Appliance -> R.drawable.baseline_coffee_maker_24
            CategoryType.BeautyAndPersonalCare -> R.drawable.baseline_face_24
            CategoryType.Bills -> R.drawable.baseline_receipt_24
            CategoryType.ComputerAndITAccessories -> R.drawable.outline_computer_24
            CategoryType.Electronics -> R.drawable.baseline_photo_camera_24
            CategoryType.FoodAndGroceries -> R.drawable.baseline_local_grocery_store_24
            CategoryType.Furniture -> R.drawable.baseline_chair_24
            CategoryType.Fashion -> R.drawable.baseline_shopping_bag_24
            CategoryType.Health -> R.drawable.baseline_health_and_safety_24
            CategoryType.HobbiesAndLeisure -> R.drawable.baseline_sports_esports_24
            CategoryType.PetSupplies -> R.drawable.outline_pets_24
            CategoryType.Subscription -> R.drawable.baseline_subscriptions_24
            else -> R.drawable.baseline_question_mark_24
        }
    }


}
