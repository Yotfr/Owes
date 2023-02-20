package com.yotfr.owes.app.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.yotfr.owes.R

sealed class NavBarScreenRoutes(
    val route: String,
    @StringRes val label: Int,
    @DrawableRes val icon: Int
) {
    object TakenDebts : NavBarScreenRoutes("takenDebts", R.string.taken_debts_label, R.drawable.ic_taken_debt)
    object GivenDebts : NavBarScreenRoutes("givenDebts", R.string.given_debts_label, R.drawable.ic_given_debts)
    object People : NavBarScreenRoutes("people", R.string.people_label, R.drawable.ic_people)
}
