package com.yotfr.owes.app.navigation

import androidx.annotation.StringRes
import com.yotfr.owes.R

sealed class NavBarScreenRoutes(val route: String, @StringRes val label: Int) {
    object TakenDebts : NavBarScreenRoutes("takenDebts", R.string.taken_debts_label)
    object GivenDebts : NavBarScreenRoutes("givenDebts", R.string.given_debts_label)
    object People : NavBarScreenRoutes("people", R.string.people_label)
}