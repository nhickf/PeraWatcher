package com.creativegrpcx.perawatcher.ui.utils

import android.icu.util.Calendar
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.creativegrpcx.perawatcher.R
import com.creativegrpcx.perawatcher.domain.model.Date
import com.creativegrpcx.perawatcher.domain.model.Time
import com.creativegrpcx.perawatcher.domain.types.CategoryType
import com.creativegrpcx.perawatcher.domain.types.WalletType
import com.creativegrpcx.perawatcher.ui.nav.ScreenRoute

object Constants {

    val MainScreenRoutes = listOf(
        ScreenRoute(route = "dashboard", name = "dashboard", isSelected = true),
        ScreenRoute(route = "statistics", name = "statistics"),
        ScreenRoute(route = "history", name = "history"),
        ScreenRoute(route = "wallet", name = "wallet"),
    )

    fun categoryIcon(categoryType: CategoryType): Int {
        return when (categoryType) {
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

    fun walletIcon(walletType: WalletType): Int {
        return when (walletType) {
            WalletType.CASH -> R.drawable.cash_100
            WalletType.CREDIT_CARD -> R.drawable.credit_card
            WalletType.SAVINGS -> R.drawable.bank
            else -> R.drawable.baseline_question_mark_24
        }
    }

    // Initializing a Calendar
    private val mCalendar = Calendar.getInstance()

    val currentDate = Date(
        year = mCalendar.get(Calendar.YEAR) ,
        month = mCalendar.get(Calendar.MONTH),
        dayOfMonth = mCalendar.get(Calendar.DAY_OF_MONTH)
    )

    val currentTime = Time(
        hourOfDay = mCalendar.get(Calendar.HOUR),
        minute = mCalendar.get(Calendar.MINUTE)
    )

    @Composable
    fun TextFieldTitle(
        text : String,
        modifier: Modifier = Modifier
    ){
        Text(
            modifier = modifier,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            text = text
        )
    }
}
